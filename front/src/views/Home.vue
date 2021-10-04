<template>
  <div class="home-body">
    <Timer />
    <header>
      <img alt="logo" src="../assets/logo.png" height="300" width="450">
    </header>
    <div class="main-service">
      <AvatarSetting />
      <HomeButton />
    </div>
  </div>
</template>

<script>
import AvatarSetting from '../components/setting/AvatarSetting.vue'
import HomeButton from '../components/buttons/HomeButton.vue'
import Timer from '../components/game/Timer.vue'
import axios from 'axios'
axios.defaults.baseURL = process.env.VUE_APP_API_URL
export default {
  name: 'Home',
  components: {
    AvatarSetting,
    HomeButton,
    Timer,
  },
}
</script>
<style>
.home-body {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}
.main-service {
  background-color: transparent;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;
}
</style>
