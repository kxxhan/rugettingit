<template>
  <div class="main-button-body p-px-5">
    <div
      id="underline-button"
      @click="createRoom"
      class="p-button-jj p-button-lg p-mb-2 p-pb-3"
    >
      <a>
        <span class="p-mr-1">방만들기</span>
        <img 
          id="stk"
          src="@/assets/sticker/star01.png" 
          alt="star" 
        > 
      </a> 
    </div>
    <div
      id="underline-button"
      @click="enterRoom"
      class="p-button-jj p-button-lg p-mb-2 p-pb-3"
    >
      <a>
        <span class="p-mr-1">입장하기</span>
        <img 
          id="stk"
          src="@/assets/sticker/star01.png" 
          alt="star" 
        >  
      </a>
    </div>
    <audio id="enter"><source id="soundSrc" src="@/assets/sounds/enter.mp3" /></audio>
  </div>
</template>

<script>
import { soundEffect } from '../api/sound.js'

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
@import "/style/custom-button.css";
/* 
.p-button-jj {
  text-decoration: none;
  font-family: "Elice Digital Baeum",sans-serif !important;
  background-color: transparent !important;
  color: rgba(0, 0, 0, 0.8) !important;
  position: relative !important;
  overflow:hidden !important;
  transition: .3s !important;
} */



#underline-button a {
  font-size: 3rem;
  color: rgba(0, 0, 0, 0.8) !important;
  text-align: center;
  text-transform: uppercase;
  position: relative;
  overflow:hidden;
  transition: .3s;
}

#underline-button a:after {
    position: absolute;
    transition: .3s;
    content: '';
    width: 0;
    left: 0;
    bottom: -0.3rem;
    height: 0.3rem;
    background: linear-gradient(to right, #FC5C7D, #6A82FB) !important;
  }

#underline-button a:hover {
  transition: .3s;
}

#underline-button a:hover:after {
  width: 100%;
  left: 0;
}

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
