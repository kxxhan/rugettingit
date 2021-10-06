<template>
  <div class="init-component">
    <div class="init-body"></div>
    <div id=quiz>
    <img src="@/assets/balloon1.png" alt="">
      <p>
      {{ quiz }}
      </p>
    </div>
    <img src="@/assets/RUGI.png" alt="">
  </div>
</template>

<script>
export default {
  name: 'GameInit',
  data: function() {
    return {
    }
  },
  methods: {
  },
  computed: {
    quiz: function () {
      return this.$store.state.room.quizList[this.$store.state.room.currentRound - 1].caption
    }
  }
}
</script>

<style>
.init-component {
  display: flex;
  flex-direction: column;
  align-items: center;
}
.init-body {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.initBody p {
  font-size: 35px;
}
#quiz p {
  position: absolute;
  top: 20rem;
  left: 5rem;
  /* background: url("@/assets/balloon1.png") no-repeat; */
}
</style>
