<template>
<div>
  <div class="d-flex justify-content-center">
    <Popper content="링크 복사 완료 🍿">
      <button class="text-center btn btn-light btn-lg mx-1" @click="copyLink()">
        초대링크 복사
      </button>
    </Popper>
    <button v-if="$store.state.super" class="text-center btn btn-light btn-lg mx-1" @click="startGame">
      게임 시작
    </button>
    <!-- 클립보드 복사용 인풋 -->
  </div>
    <input type="text" id="copyText" style="opacity: 0;">
</div>
</template>

<script>
import axios from 'axios'
import Popper from "vue3-popper";

export default {
  emits: ['viewChange'],
  name: 'LobbyButton',
  components: {
    Popper,
  },
  data: function() {
    return {
      inviteLink: document.location.origin + '/' + document.location.search,
    }
  },
  methods :{
    copyLink: function() {
      const copyText = document.getElementById("copyText"); 
      copyText.value = this.inviteLink;
      copyText.focus()
      copyText.select()
      document.execCommand('copy')
    },
    startGame: function () {
      console.log("게임 시작");
      axios({
        method: 'post',
        url: '/game',
        params: {
          roomId: this.$store.state.currentRoomId
        }
      }).then((res) => {
        console.log('게임 시작 완료', res)
      }).catch((err) => {
        console.log(err.response)
      })
    },
  },
  mounted: function() {
  }
}
</script>

<style>
  .p-button-raised {
    margin-right: 5px;
  }

    :root {
    --popper-theme-text-color: #33333;
    --popper-theme-border-width: 0px;
    --popper-theme-border-style: solid;
    --popper-theme-border-radius: 3rem 3rem 3rem 3rem;
    --popper-theme-padding: 10px;
    --popper-theme-box-shadow: 0 6px 30px -6px rgba(0, 0, 0, 0.25);
  }
</style>
