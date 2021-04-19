<template>
  <div class="peripheral-layout">
    <div style="display: flex; flex-direction: row">
      <span class="option-text">Peripheral {{ position + 1 }}: Details</span>
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
          placeholder="Vendor name"
          :rules="nameRules"
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
              :disabled="disableForDetails"
              style="width: 80%; margin-top: -20px"
              readonly
              :rules="dateSelectionRules"
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
          :label="`Switch 1: ${peripheral.status.toString()}`"
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
      // peripheral:{
      //   vendor: '',
      //   date: '',
      //   status: true
      // },
      dateSelectionRules: [
        (v) => !!v || "Date is required"
      ],
      nameRules: [
       // v => !!v || 'Input is required',
        //v => v.length <= 15 || "Package name must not have more than 15 characters"
      ],
      detailsRules: [
       // v => !!v || 'Input is required',
       // v => v.length <= 50 || "Package details must not have more than 50 characters"
      ],
      priceRules: [
       // v => !!v || 'Input is required',
       // v => v && v.length < 10 || 'Package price must be less than 10 digits'
      ]
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
