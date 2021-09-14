<template>
  <div class="home">
    <img alt="Vue logo" src="../assets/logo.png">
  </div>
  <div>서비스메인화면</div>
  <Canvas />
</template>

<script>
import Canvas from '../components/game/Canvas.vue'

export default {
  components: {
    Canvas
  },
  name: 'Home',
}
</script>
