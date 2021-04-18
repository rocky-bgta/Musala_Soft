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
          dense
          required
          style="width: 80%">
      </v-text-field>

      <v-textarea
          outlined
          v-model="gateway.ipv4address"
          style="width: 80%"
          label="Enter IPv4 address"
          value="">
      </v-textarea>


      <peripheral v-for="(peripheral, index) in peripherals"
                      @addPeripheral="addPeripheral"
                      @deletePeripheral="deletePeripheral"
                      :pkg-count="peripherals.length"
                      :peripheral="peripheral"
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
               :disabled="!isValid">Create
        </v-btn>

        <v-btn v-if="this.pageInUpdateState==true"
               @click="updateGateway"
               color="#e2136e"
               class="primary"
               style="color: #FFFFFF"
               :disabled="!isValid">Update
        </v-btn>
      </v-row>
    </div>
  </v-form>
</template>

<script>

import Peripheral from "@/components/Peripheral";

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
        peripheral:{
          vendor: '',
          date: '',
          status: true
        },
        peripherals:[],
        dialog: false,
        isValid: false,
        pageInUpdateState: false,
        gatewayNameRule: [
          (v) => !!v || 'Gateway Name is required'
        ]
      };
    },
    created() {
      this.peripherals.push(this.peripheral);
    },
    methods: {

      addPeripheral(){
        this.peripherals.push({vendor: '',date:'',status: ''});
      },

      deletePeripheral(position){
        this.peripherals.splice(position, 1);
        if(this.peripherals.length==0){
          this.peripherals.push(this.peripheral);
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
      },
      createGateway() {
        console.log('Peripherals info: ', JSON.stringify(this.peripherals, null, 2));

        // this.$restClient.post('create', this.gateway)
        //    .then(({data}) => {
        //      console.log(this.$httpStatusCode.OK);
        //      if (data.httpStatusCode === this.$httpStatusCode.OK) {
        //        this.changeDialogStatus();
        //        this.resetForm();
        //        this.$eventBus.$emit(this.$evenBusConstant.REFRESH_TODO_LIST);
        //      }
        //      this.$feedback.showSuccessMessage(data.message);
        //    })
        //    .catch(({data}) => {
        //      this.$feedback.showFailed(data.message);
        //      this.changeDialogStatus();
        //    });
      },
      updateGateway() {
        this.$feedback.showLoading();
        this.$restClient.put('update', this.gateway)
           .then(({data}) => {
             if (data.httpStatusCode === this.$httpStatusCode.OK) {
               this.changeDialogStatus();
               this.resetForm();
               this.$eventBus.$emit(this.$evenBusConstant.REFRESH_TODO_LIST);
             }
             this.$feedback.showSuccessMessage(data.message);
           })
           .catch(({data}) => {
             this.$feedback.showFailed(data.message);
             this.changeDialogStatus();
           });
      },
    },
    updated() {
      let copyPayload;
      this.$eventBus.$on(this.$evenBusConstant.PASS_TODO_ITEM_FOR_EDIT, (payload) => {
        copyPayload = Object.assign({}, payload);
        if (!this.$validation.isEmptyObject(copyPayload)) {
          this.todo = copyPayload;
          this.pageInUpdateState = true;
        }
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
