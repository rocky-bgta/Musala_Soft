<template>
  <div class="peripheral-layout">
    <div style="display: flex; flex-direction: row">
      <span class="option-text">Peripheral No: {{ position + 1 }}</span>
      <v-spacer></v-spacer>
      <div class="delete-div" v-if="isDeletable" @click="$emit('deletePeripheral', position)">
        <img class="delete-icon" src="@/assets/icon/delete-icon.svg">
        <span style="color:#d0021b;">Delete</span>
      </div>

    </div>
    <v-form v-model="isValid">
      <v-text-field
          v-model="peripheral.vendor"
          label="vendor name"
          :rules="vendorNameRule"
          placeholder="Vendor name"
          :disabled="disableForDetails"
          dense
          required
          type="text"
          outlined>
      </v-text-field>
      <v-menu
          v-model="menu2"
          :close-on-content-click="false"
          :nudge-right="40"
          transition="scale-transition"
          offset-y
          min-width="auto">
        <template v-slot:activator="{ on, attrs }">
          <v-text-field
              v-model="peripheral.createdDate"
              prepend-icon="mdi-calendar"
              label="Pick a date"
              :rules="dateSelectionRule"
              :disabled="disableForDetails"
              style="width: 80%"
              readonly
              v-bind="attrs"
              v-on="on"
          ></v-text-field>
        </template>
        <v-date-picker
            v-model="peripheral.createdDate"
            @input="menu2 = false">
        </v-date-picker>
      </v-menu>

      <span class="option-text">Status</span>
      <v-switch
          v-model="peripheral.status"
          :disabled="disableForDetails"
          :label="checkDeviceStatus"
      ></v-switch>

    </v-form>
    <v-btn color="primary"
           class="add-peripheral-btn"
           v-show="isAddVisible"
           width="150"
           height="32"
           required
           @click="$emit('addPeripheral')"
           rounded>
      Add Peripheral
      <v-spacer></v-spacer>

    </v-btn>
  </div>
</template>

<script>

export default {
  name: "Peripheral",
  props: ['position', 'peripheralCount', 'peripheral', 'disableForDetails'],
  data() {
    return {
      isValid: true,
      menu2: false,
      vendorNameRule: [
        (v) => !!v || 'Vendor Name is required'
      ],
      dateSelectionRule: [
        (v) => !!v || 'Creation Date is required'
      ],
    }
  },
  computed: {
    isDeletable() {
      if(this.disableForDetails)
        return false
      else return this.peripheralCount > 1
    },
    isAddVisible() {
      if(this.disableForDetails)
        return false
      else return this.peripheralCount < 10;
    },
    checkDeviceStatus(){
      if(this.peripheral.status)
        return "Online";
      else return "Offline";
    }

  },
}
</script>

<style scoped>
.add-peripheral-btn {
  font-size: 12px;
  margin-top: 15px;
}

.option-text {
  font-size: 14px;
  font-weight: normal;
  font-stretch: normal;
  font-style: normal;
  line-height: 1.29;
  letter-spacing: normal;
  text-align: left;
  color: #464646;
  margin-bottom: 15px;
}

.delete-div {
  display: flex;
  flex-direction: row;
  line-height: 1;
  cursor: pointer;
}

.delete-icon {
  width: 13.1px;
  height: 15px;
  margin-right: 5px;
}

.peripheral-layout {
  display: flex;
  flex-direction: column;
  padding: 16px;
  width: 500px;
  border-radius: 4px;
  border: solid 1px #f3f3f3;
  background-color: #fafafa;
  margin-bottom: 15px;
}
</style>
