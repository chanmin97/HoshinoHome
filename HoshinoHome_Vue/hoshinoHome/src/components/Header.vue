<script setup>
import { ref, onMounted } from 'vue'
import { useRouter, RouterLink, useRoute } from 'vue-router'
import Login from '../components/User/Login.vue' // Login 컴포넌트 임포트
import Register from '../components/User/Register.vue' // Register 컴포넌트 임포트
import MyPage from '../components/User/MyPage.vue' // MyPage 컴포넌트 임포트

const router = useRouter()
const route = useRoute()

// 로그인 상태를 저장할 ref
const isLoggedIn = ref(false)
const showLoginModal = ref(false) // 로그인 모달 표시 상태를 저장할 ref
const showRegisterModal = ref(false) // 회원가입 모달 표시 상태를 저장할 ref
const showMyPageModal = ref(false) // 마이페이지 모달 표시 상태를 저장할 ref

// JWT 토큰 확인 함수
const checkLoginStatus = () => {
  const token = localStorage.getItem('token')
  isLoggedIn.value = !!token
}

// 컴포넌트가 마운트될 때 로그인 상태를 확인
onMounted(() => {
  checkLoginStatus()
})

// 로그아웃 함수
const logout = () => {
  localStorage.removeItem('token')
  localStorage.removeItem('kakao_209b21c4ea1644f6b617f33542bc6dd8')
  isLoggedIn.value = false
  router.push('/').then(() => {
    window.location.reload() // 홈 화면으로 이동 후 새로고침
  })
}

// 모달 토글 함수
const toggleLoginModal = () => {
  showLoginModal.value = !showLoginModal.value
}

const toggleRegisterModal = () => {
  showRegisterModal.value = !showRegisterModal.value
}

const toggleMyPageModal = () => {
  showMyPageModal.value = !showMyPageModal.value
}
</script>

<template>
  <!-- Header Section -->
  <header
    class="bg-gradient-to-r from-gray-900 via-gray-800 to-gray-700 bg-opacity-70 text-white py-4 px-6 flex justify-between items-center shadow-lg backdrop-filter backdrop-blur-lg custom"
  >
    <div class="flex items-center space-x-5">
      <!-- Home Icon -->
      <RouterLink to="/">
        <img src="/LastLogo.png" alt="Home" class="cursor-pointer" style="width: 175px; height: 35px;"/>
      </RouterLink>
      
      <!-- <RouterLink 
        to="/about" 
        class="text-lg hover:text-gray-400 transition duration-300"
        active-class="text-blue-400" exact-active-class="text-blue-400"
      >비타민</RouterLink> -->
      <RouterLink 
        to="/notice" 
        class="text-lg hover:text-gray-400 transition duration-300"
        active-class="text-blue-400" exact-active-class="text-blue-400"
      >공지사항</RouterLink>
      <RouterLink 
        to="/map" 
        class="text-lg hover:text-gray-400 transition duration-300"
        active-class="text-blue-400" exact-active-class="text-blue-400"
      >매물</RouterLink>
      <RouterLink 
        to="/dongStory" 
        class="text-lg hover:text-gray-400 transition duration-300"
        active-class="text-blue-400" exact-active-class="text-blue-400"
      >지역이야기</RouterLink>

    </div>

    <nav class="space-x-4 flex text-lg">
      <!-- 로그인 하기 전 보일 부분 -->
      <template v-if="!isLoggedIn">
        <span
          @click="toggleLoginModal"
          class="ml-5 text-lg cursor-pointer hover:text-gray-400 transition duration-300"
        >로그인</span>
        <span
          @click="toggleRegisterModal"
          class="ml-5 text-lg cursor-pointer hover:text-gray-400 transition duration-300"
        >회원가입</span>
      </template>
      <!-- 로그인이 유지되는 중 보일 부분 -->
      <template v-else>
        <span
          @click="logout"
          class="ml-5 text-lg cursor-pointer hover:text-gray-400 transition duration-300"
        >로그아웃</span>
        <span
          @click="toggleMyPageModal"
          class="ml-5 text-lg cursor-pointer hover:text-gray-400 transition duration-300"
        >마이페이지</span>
      </template>
    </nav>
  </header>

  <!-- Login Modal -->
  <div
    v-if="showLoginModal"
    class="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50"
  >
    <div class="bg-white p-8 rounded-lg shadow-lg w-96 relative transition duration-300">
      <button
        @click="toggleLoginModal"
        class="absolute top-2 right-2 text-gray-500 hover:text-gray-800 transition duration-300"
      >
        &times;
      </button>
      <Login @close="toggleLoginModal" />
    </div>
  </div>

  <!-- Register Modal -->
  <div
    v-if="showRegisterModal"
    class="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50"
  >
    <div class="bg-white p-8 rounded-lg shadow-lg w-96 relative transition duration-300">
      <button
        @click="toggleRegisterModal"
        class="absolute top-2 right-2 text-gray-500 hover:text-gray-800 transition duration-300"
      >
        &times;
      </button>
      <Register @close="toggleRegisterModal" />
    </div>
  </div>

  <!-- MyPage Modal -->
  <div
    v-if="showMyPageModal"
    class="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50"
  >
    <div class="bg-white p-8 rounded-lg shadow-lg w-96 relative transition duration-300">
      <button
        @click="toggleMyPageModal"
        class="absolute top-2 right-2 text-gray-500 hover:text-gray-800 transition duration-300"
      >
        &times;
      </button>
      <MyPage @close="toggleMyPageModal" />
    </div>
  </div>
</template>

<style scoped>
body {
  background-color: #f3f4f6;
}
</style>
