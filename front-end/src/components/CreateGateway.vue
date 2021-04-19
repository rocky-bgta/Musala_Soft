<template>
  <div class="text-center">
    <v-dialog
        v-model="dialog"
        width="535"
        @click:outside="outSideCloseOfGatewayForm">

      <template v-slot:activator="{ on, attrs }">
        <v-btn
            color="primary"
            dark
            v-bind="attrs"
            v-on="on">
          Add Gateway
        </v-btn>
      </template>

      <v-card>
        <gateway-form ref="gatewayFormRef" :change-dialog-status="changeDialogStatus"></gateway-form>
      </v-card>
    </v-dialog>


  </div>
</template>

<script>
  import GatewayForm from '@/components/GatewayForm';

  export default {
    name: 'CreateGateway',
    components: {
      gatewayForm: GatewayForm,
    },
    data() {
      return {
        dialog: false,
      };
    },
    methods: {
      changeDialogStatus() {
        this.dialog = false;
      },
      showTodoDialog() {
        this.dialog = true;
      },
      outSideCloseOfGatewayForm(){
        this.$refs.gatewayFormRef.resetForm();
      }
    },
    mounted() {
      this.$eventBus.$on(this.$evenBusConstant.SHOW_GATEWAY_FORM, () => {
        this.showTodoDialog();
      });
    }
  };
</script>

<style scoped>
</style>
