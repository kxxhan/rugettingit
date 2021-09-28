<template>
<div>
  <div>
    <component :is="currentView">
    </component>
  </div>
  
  <div>
    <Chat />
  </div>
</div>
</template>

<script>
import Lobby from '@/components/game/Lobby.vue'
import GameInit from '@/components/game/GameInit.vue'
import GamePlay from '@/components/game/GamePlay.vue'
import GameResult from '@/components/game/GameResult.vue'
import Chat from '@/components/game/Chat.vue'

export default {
  name: 'Game',
  components: {
    Lobby,
    GameInit,
    GamePlay,
    GameResult,
    Chat
  },
  data: function() {
    return {
      //currentView의 default값은 lobby로 해야 할 것인가?
      currentView: 'Lobby'
    }
  },
  methods: {

  }
}
</script>

<style>

</style>
