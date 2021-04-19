<template>
  <v-app>
    <v-app-bar
      app
      color="white"
      dark
      height="120px"
    >
      <div class="d-flex align-center">
        <v-img
          alt="Vuetify Name"
          class="shrink mt-1 hidden-sm-and-down"
          contain
          min-width="100"
          src="@/assets/musala_Logo_green.svg"
          width="100"
        />
      </div>

      <v-spacer></v-spacer>

      <v-btn
        href="https://www.musala.com/company/"
        target="_blank"
        text
      >
        <span class="mr-2" style="color: black">Musala Soft</span>
        <v-icon>mdi-open-in-new</v-icon>
      </v-btn>

    </v-app-bar>

    <v-main>
      <Gateway/>
    </v-main>
  </v-app>
</template>

<script>
import Gateway from './components/GatewayList';

export default {
  name: 'App',

  components: {
    // eslint-disable-next-line vue/no-unused-components
    Gateway,
  },

  data: () => ({
    //
  }),
};
</script>
