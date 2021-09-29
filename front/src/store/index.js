import { createStore } from 'vuex'
import createPersistedState from 'vuex-persistedstate'

export default createStore({
  state: {
    id: '',
    avatar: '',
    nickname: 'nickname',
    roomId: '',
    stompClient: ''
  },
  mutations: {
    SET_USERDATA: function (state, data) {
      state.id = data.id
      state.avatar = data.avatar
      state.nickname = data.nickname
    },
    SET_NICKNAME: function (state, data) {
      state.nickname = data
    },
    SET_ROOMID: function (state, data) {
      state.roomId = data
    },
    SET_AVATAR: function (state, data) {
      state.avatar = data
    },
    SET_STOMP_CLIENT: function(state, data) {
      state.stompClient = data
    }
  },
  actions: {
    setUserData: function(context, data) {
      context.commit('SET_USERDATA', data)
    },
    setNickName: function(context, data) {
      context.commit('SET_NICKNAME', data)
    },
    setRoomId: function(context, data) {
      context.commit('SET_ROOMID', data)
    },
    setAvatar: function(context, data) {
      context.commit('SET_AVATAR', data)
    },
    setStompClient: function(context, data) {
      context.commit('SET_STOMP_CLIENT', data)
    }
    
  },
  modules: {
  },
  plugins: [createPersistedState()]
})
