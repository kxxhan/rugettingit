<template>
  <div class="home-body p-d-flex">
    <header>
      <img alt="logo" src="../assets/logo.png" height="550">
    </header>
    <div class="main-service">
      <div class="p-col-6">
        <AvatarSetting />
      </div>
      <div class="p-col-6">
        <HomeButton />
      </div>
    </div>
  </div>
</template>

<script>
import AvatarSetting from '../components/setting/AvatarSetting.vue'
import HomeButton from '../components/buttons/HomeButton.vue'
import axios from 'axios'
axios.defaults.baseURL = process.env.VUE_APP_API_URL
export default {
  name: 'Home',
  components: {
    AvatarSetting,
    HomeButton,
  },
}
</script>
<style>
.home-body {
  display: flex;
  flex-direction: column;
  align-items: between;
  justify-content: center;
  padding-bottom: 3rem;
}
.main-service {
  background-color: transparent;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;
}
</style>
