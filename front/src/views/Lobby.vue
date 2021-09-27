<template>
  <div class="lobbyBody">
    <header>
      <img alt="Vue logo" src="@/assets/ccc.png" height="200" width="200">
    </header>
    <div class="lobbyComponents">
      <UserList />
      <div class="gameBody">
        <GameSetting />
        <LobbyButton />
      </div>
    </div>
  </div>
</template>

<script>
import Stomp from 'webstomp-client'
import SockJS from 'sockjs-client'

import LobbyButton from '@/components/buttons/LobbyButton.vue'
import GameSetting from '@/components/setting/GameSetting.vue'
import UserList from '@/components/user/UserList.vue'

export default {
  components: {
    GameSetting,
    LobbyButton,
    UserList
  },
  name: 'Lobby',
  data() {
    return {
      roomId: this.$route.query.room,
      nickname: this.$store.state.nickname,
      message: "",
      recvList: []
    }
  },
  methods: {
    sendMessage (e) {
      if(e.keyCode === 13 && this.userName !== '' && this.message !== ''){
        this.send()
        this.message = ''
      }
    },
    send() {
      console.log("Send message:" + this.message)
      if (this.stompClient && this.stompClient.connected) {
        const msg = { 
          userName: this.nickname,
          content: this.message 
        }
        this.stompClient.send("/pub", JSON.stringify(msg), {})
      }
    }, 
    connect() {
      const serverURL = "http://localhost:8080/api/stomp/chat"
      let socket = new SockJS(serverURL);
      this.stompClient = Stomp.over(socket);
      console.log(`소켓 연결을 시도합니다. 서버 주소: ${serverURL}`)
      this.stompClient.connect(
        {},
        frame => {
          // 소켓 연결 성공
          this.connected = true
          console.log('소켓 연결 성공', frame)
          // 서버의 메시지 전송 endpoint를 구독합니다.
          // 이런형태를 pub sub 구조라고 합니다.
          console.log('roomID:' + this.roomId)
          this.stompClient.subscribe("/sub/chat/room/" + this.roomId, chat => {
            // 받은 데이터를 json으로 파싱하고 리스트에 넣어줍니다.
            this.recvList.push(JSON.parse(chat.body))
            console.log('구독으로 받은 메시지 입니다.', chat.body)
          })
        },
        error => {
          // 소켓 연결 실패
          console.log('소켓 연결 실패', error)
          this.connected = false
        }
      )        
    }
  },
  created() {
      // 로비에 입장하면 소켓 연결 시도 핸드셰이킹 요청
    this.connect()
  },  
}

</script>

<style>
.lobbyBody {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding-top: 50px;
  padding: 50px 0px;
}
.lobbyComponents {
  display: flex;
  flex-direction: row;
  align-items: center;
  padding-top: 50px;
  padding: 50px 0px;
}
.gameBody {
  display: flex;
  flex-direction: column;
  padding: 0px 50px;
}
</style>
