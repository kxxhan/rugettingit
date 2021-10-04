<template>
  <div class="main-button-body">
    <button
      id="createSession"
      @click="createRoom"
      class="p-button-jj p-button-lg p-mb-2"
    >
      <span class="p-mr-1">방만들기</span>
      <img class="" src="@/assets/sticker/star01.png" alt="" id="stk">  
    </button>
    <Button
      id="unique-transparent-btn"
      @click="enterRoom"
      class="p-button-jj p-button-lg p-mb-2 unique-transparent-btn"
    >
    <a>
      <span class="p-mr-1">입장하기</span>
      <img class="" src="@/assets/sticker/star01.png" alt="" id="stk">  
    </a>
    </Button>
<!--  test  -->
    <button class="btn btn-lg p-3" id="unique-transparent-btn">
      <a>
        <span>로그인하러 가기</span>
      </a> 🌟
    </button>


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

/* .highlight {
  display: inline;
  box-shadow: inset 0 -10px 0 #D9FCDB; 
}

.highlight:after{
  content:"";
  width: 0;
  height: 10px;
  display: inline-block;
  background: #D9FCDB;
} */



#unique-transparent-btn a {
  /* margin: 10px;
  padding: 20px; */
  color: #000084;
  text-align: center;
  text-transform: uppercase;
  position: relative;
  overflow:hidden;
  transition: .3s;
}
#unique-transparent-btn a:after {
    position: absolute;
    transition: .3s;
    content: '';
    width: 0;
    left: 0;
    bottom: -3px;
    height: 2px;
    background: #000084;
  }

#unique-transparent-btn a:hover {
  /* cursor: pointer; */
  /* background-color: #fffcc8; */
  transition: .3s;
}

#unique-transparent-btn a:hover:after {
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
