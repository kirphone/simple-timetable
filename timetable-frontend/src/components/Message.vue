<template>
  <div id="message">
    Привет, аренда домена сайта подходит к концу. Если кому-то нужен этот сайт, то можете отправить любую сумму по контактам внизу страницы. Также сообщаю, что
    правильная ссылка на сайт это <a href="https://simple-timetable.ru">https://simple-timetable.ru</a>, где нет рекламы, которую мог добавлять ваш провайдер</div>
</template>

<script>
export default {
  name: "Message"
}
</script>

<style scoped>
  #message{
    border: 2px solid #fc05d6;
    font-family: ElMessiri;
    font-size: 15px;
    line-height: 21px;
    animation: back 12s infinite;
  }
  @media (max-width: 500px) {
    #message{
      font-size: 13px;
      line-height: 16px;
    }
  }

  @media (max-width: 380px) {
    #message{
      font-size: 10px;
      line-height: 12px;
    }
  }

  @media (max-width: 280px) {
    #message{
      font-size: 8px;
      line-height: 10px;
    }
  }
  @keyframes back {
    from {background-color: #319A1F; }
    25% {background-color: #ffb845;}
    50% {background-color: #f7dd00;}
    75% {background-color: #0cc2de;}
    to {background-color: #319A1F; }
  }
</style>