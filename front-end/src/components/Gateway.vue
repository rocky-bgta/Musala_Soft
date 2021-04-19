<template>
  <v-container>
    <v-row class="text-center">

      <v-col class="mb-4">
        <h1 class="display-2 font-weight-bold mb-3">
          Gateway List
        </h1>
        <create-gateway></create-gateway>

        <v-data-table
            :headers="headersForGatewayTable"
            :items-per-page=numberOfGateway
            :items="gatewayList"
            class="elevation-1"
            style="margin-top: 20px"
            :hide-default-footer="true"
            no-data-text="Gateway didn't create yet..">

          <template v-slot:item.action="{item}">
            <div class="action-button-container">
              <v-chip outlined
                      @click="editTodo(item)"
                      color="info">Edit
              </v-chip>
              <v-chip
                  outlined
                  color="success"
                  style="margin-left: 15px"
                  @click="doneTodo(item)">Done
              </v-chip>
            </div>
          </template>
        </v-data-table>

      </v-col>
    </v-row>
  </v-container>
</template>

<script>
  import CreateGateway from '@/components/CreateGateway';

  export default {
    name: 'Todo',
    components: {
      CreateGateway
    },
    created() {
      this.getGatewayList();
    },
    updated() {
      // this.$eventBus.$on(this.$evenBusConstant.REFRESH_TODO_LIST, () => {
      //   this.getTodoList();
      // });
    },
    data() {
      return {
        numberOfGateway: 0,
        headersForGatewayTable: [
          {
            text: 'Gateway Name',
            align: 'start',
            sortable: false,
            width: '20%',
            value: 'name',
            class: 'table-header-text'
          },
          {text: 'IPV4 Address', value: 'ipv4address', width: '45%', class: 'table-header-text'},
          {text: 'Action', value: 'action', width: '20%', class: 'table-header-text'}
        ],
        gatewayList: []
      };
    },
    methods: {
      async editTodo(item) {
        await new Promise(resolve => {
          this.$eventBus.$emit(this.$evenBusConstant.SHOW_TODO_FORM);
          resolve();
        });
        this.$eventBus.$emit(this.$evenBusConstant.PASS_TODO_ITEM_FOR_EDIT, item);
      },

      async doneTodo(item) {
        let confirmed = await this.$feedback.getConfirmation();
        if (!confirmed) return;

        this.$restClient.delete('delete/'+item.id)
           .then(({data}) => {
             if (data.httpStatusCode === this.$httpStatusCode.OK) {
               this.getTodoList();
             }
             this.$feedback.showSuccessMessage(data.message);
           }).catch(({data}) => {
              this.$feedback.showFailed(data.message);
           });
      },

      getGatewayList() {
        this.$restClient.get('list')
           .then(({data}) => {
             this.gatewayList = data.data;
             this.numberOfGateway = parseInt(this.gatewayList.length);
           })
           .catch(({data}) => {
             this.$feedback.showFailed(data.message);
           });
      }
    }
  };
</script>
