<template>
  <div class="main-button-body">
    <Button
      id="createSession"
      @click="createRoom"
      class="p-button-jj p-button-lg p-mb-2"
    >
      <span class="p-mr-1">방만들기</span>
      <img class="" src="@/assets/sticker/star01.png" alt="" id="stk">  
    </Button>
    <button
      id="enterSession"
      @click="enterRoom"
      class="p-button-jj p-button-lg p-mb-2"
    >
      <span class="p-mr-1">입장하기</span>
      <img class="" src="@/assets/sticker/star01.png" alt="" id="stk">  
    </Button>
  </div>
</template>

<script>
import { soundEffect } from '../api/sound.js'
export default {
  name: 'MainButton',
  methods: {
    createRoom: function () {
      soundEffect('https://soundbible.com/mp3/sms-alert-1-daniel_simon.mp3')  // 소리는 쓰고싶은거 어디 올려놓고 써도 될거같음
      this.$store.dispatch("createRoom")
    },
    enterRoom: function () {
      soundEffect('https://soundbible.com/mp3/sms-alert-1-daniel_simon.mp3')
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
@import "/style/custom-button.css";

#stk {
  max-height: 2rem;
}
.main-button-body {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}
</style>
