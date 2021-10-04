<template>
  <div class="mainButtonBody">
    <Button
      id="createSession"
      @click="createRoom"
      class="p-button-raised p-button-text p-button-secondary p-button-lg"
      label="방만들기"
    >
    </Button>
    <Button
      id="enterSession"
      @click="enterRoom"
      class="p-button-raised p-button-text p-button-secondary p-button-lg"
    >
      입장하기
    </Button>
    <audio id="enter"><source id="soundSrc" src="@/assets/sounds/enter.mp3" /></audio>
  </div>
</template>

<script>
import {soundEffect} from '../api/sound.js'
export default {
  name: 'MainButton',
  methods: {
    createRoom: function () {
      soundEffect('#enter')
      this.$store.dispatch("createRoom")
    },
    enterRoom: function () {
      soundEffect('#enter')
      const roomId = this.$route.query["room"];
      console.log(this.$route.query["room"]);
      if (roomId) {
        this.$router.push( {name : 'Game', query: {room: roomId}})
      } else {
        console.log("roomId가 없어요", roomId); // roomId가 없어요! Random 방 입장
      }
    },
  }
}
</script>

<style>
.mainButtonBody {
  display: flex;
  flex-direction: column;
  align-items: center;
}
.p-button-raised {
  margin-top: 25px !important;
  margin-left: 25px !important;
}
</style>
