<template>
  <v-form v-model="isValid">
    <div class="gateway_form">
      <span v-if="this.pageInUpdateState==false" class="gateway_form_title">Create Gateway</span>
      <span v-if="this.pageInUpdateState==true" class="gateway_form_title">Update Gateway</span>
      <v-text-field
          v-model="gateway.name"
          label="Enter gateway name"
          :rules="gatewayNameRule"
          outlined
          :disabled="isDisable"
          dense
          required
          style="width: 80%; margin-left: -80px;">
      </v-text-field>

      <v-text-field
          outlined
          :disabled="isDisable"
          dense
          required
          :rules="ipv4addressRule"
          v-model="gateway.ipv4address"
          style="width: 80%; margin-left: -80px;"
          label="Enter IPv4 address"
          value="">
      </v-text-field>


      <peripheral v-for="(peripheral, index) in peripherals"
                      @addPeripheral="addPeripheral"
                      @deletePeripheral="deletePeripheral"
                      :peripheral-count="peripherals.length"
                      :peripheral="peripheral"
                      :disable-for-details="isDisable"
                      :position="index" :key="index"></peripheral>



      <v-row style="margin-bottom: 60px;margin-top: 10px">
        <v-btn style="margin-right: 10px"
            depressed
            color="error"
            @click="getCancel">
          Cancel
        </v-btn>

        <v-btn v-if="this.pageInUpdateState==false"
               @click="createGateway"
               color="#e2136e"
               class="primary"
               style="color: #FFFFFF"
               :disabled="!isValid || isDisable || !disableButtonForPeripheral">Create
        </v-btn>

        <v-btn v-if="this.pageInUpdateState==true"
               @click="updateGateway"
               color="#e2136e"
               class="primary"
               style="color: #FFFFFF"
               :disabled="!isValid || !disableButtonForPeripheral">Update
        </v-btn>
      </v-row>
    </div>
  </v-form>
</template>

<script>

import Peripheral from "@/components/PeripheralForm";
import validation from "@/utilities/validation";

  export default {
    name: 'GatewayForm',
    components:{
      Peripheral
    },
    props: {
      changeDialogStatus: Function
    },
    data() {
      return {
        gateway: {
          id: '',
          name: '',
          ipv4address:''
        },
        isDisable:false,
        peripherals:[],
        dialog: false,
        isValid: false,
        pageInUpdateState: false,
        gatewayNameRule: [
          (v) => !!v || 'Gateway Name is required'
        ],
        ipv4addressRule:[
          (v) => !!v || 'IPv4 address is required',
          (v) => /\b((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)(\.|$)){4}\b/.test(v)
              || 'Provide valid IPv4 address'
        ],
      };
    },
    methods: {

      addPeripheral(){
        this.peripherals.push({vendor: '',createdDate:'',status: false});
      },

      deletePeripheral(position){
        this.peripherals.splice(position, 1);
        if(this.peripherals.length==0){
          this.peripherals.push({vendor: '',createdDate:'',status: false});
        }
      },

      getCancel() {
        this.changeDialogStatus();
        this.resetForm();
      },

      resetForm() {
        this.gateway.id = '';
        this.gateway.name = '';
        this.gateway.ipv4address = '';
        this.pageInUpdateState = false;
        this.peripherals.splice(1,this.peripherals.length);
        this.peripherals[0].vendor='';
        this.peripherals[0].createdDate='';
        this.peripherals[0].status=false;
        this.isDisable = false;
      },

      createGateway() {
        this.gateway.peripheralList= this.peripherals;

        this.$restClient.post('create', this.gateway)
           .then(({data}) => {
             console.log(this.$httpStatusCode.OK);
             if (data.httpStatusCode === this.$httpStatusCode.OK) {
               this.changeDialogStatus();
               this.resetForm();
               this.$eventBus.$emit(this.$evenBusConstant.REFRESH_GATEWAY_LIST);
             }
             this.$feedback.showSuccessMessage(data.message);
           })
           .catch(({data}) => {
             this.$feedback.showFailed(data.message);
             this.changeDialogStatus();
           });
      },

      getGatewayById(id) {
        this.$restClient.get('get/'+id)
            .then(({data}) => {
              this.gateway = data.data;
              this.peripherals = this.gateway.peripheralList;
            })
            .catch(({data}) => {
              this.$feedback.showFailed(data.message);
            });
      },

      updateGateway() {
        this.$feedback.showLoading();
        this.gateway.peripheralList= this.peripherals;
        this.$restClient.put('update', this.gateway)
           .then(({data}) => {
             if (data.httpStatusCode === this.$httpStatusCode.OK) {
               this.changeDialogStatus();
               this.resetForm();
               this.$eventBus.$emit(this.$evenBusConstant.REFRESH_GATEWAY_LIST);
             }
             this.$feedback.showSuccessMessage(data.message);
           })
           .catch(({data}) => {
             this.$feedback.showFailed(data.message);
             this.changeDialogStatus();
           });
      },
    },
    computed:{
      disableButtonForPeripheral(){
        let isValid = true;
        for (let peripheral of this.peripherals) {
          isValid = validation.isAllPropertyOfObjectContainValue(peripheral);
          if (isValid == false) {
            isValid = false;
            break;
          }
        }
        return isValid;
      }
    },
    created() {
      this.isDisable = false;
      this.peripherals.push({vendor: '',createdDate:'',status: false});

      this.$eventBus.$on(this.$evenBusConstant.PASS_GATEWAY_ITEM_FOR_EDIT, (payload) => {
        this.getGatewayById(payload.id)
        this.pageInUpdateState = true;
        this.isDisable = false;
      });

      this.$eventBus.$on(this.$evenBusConstant.PASS_GATEWAY_ITEM_FOR_DETAILS, (payload) => {
        this.getGatewayById(payload.id)
        this.pageInUpdateState = false;
        this.isDisable = true;
      });
    },
  };
</script>

<style scoped>

  .gateway_form {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
  }

  .gateway_form_title {
    font-size: 22px;
    font-weight: normal;
    font-stretch: normal;
    font-style: normal;
    line-height: 0.82;
    letter-spacing: normal;
    text-align: center;
    color: #464646;
    margin-top: 50px;
    margin-bottom: 30px;
  }

</style>
