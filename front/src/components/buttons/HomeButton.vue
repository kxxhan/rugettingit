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
  </div>
</template>

<script>
export default {
  name: 'MainButton',

  methods: {
    createRoom: function () {
      this.$store.dispatch("createRoom")
    },
    enterRoom: function () {
      if (this.$store.state.roomId) {
        this.$router.push( {name : 'Game', query: {room: this.$store.state.roomId}})
      } else {
        // roomId가 없어요! Random 방 입장
      }
    },
  },
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
