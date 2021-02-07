<template>
  <div id="footer">
    <div class = "contacts">
      <div id="vk_contact">
        <img src="../assets/svg/vk_icon.svg" class="contact_icon">
        <a v-on:click="trackOutboundLink('https://vk.com/fucksocietyone')" href="https://vk.com/fucksocietyone">Создатель</a>
      </div>
      <div id="tg_contact">
        <img src="../assets/svg/telegram_icon.svg" class="contact_icon">
        <span>@kirphone</span>
      </div>
    </div>
    <div class = "card">
      Карта: 4817760254866746 (сначала желательно написать)
    </div>
  </div>
</template>

<script>
export default {
  name: "BaseFooter",
  methods: {
    trackOutboundLink(url) {
      // eslint-disable-next-line no-undef
      gtag('event', 'click', {
        'event_category': 'Переходы в вк',
        'event_label': url,
        'transport_type': 'beacon',
        'event_callback': function(){document.location = url;}
      });
    }
  }
}
</script>

<style scoped>
  .contact_icon {
    width: 18px;
    height: 18px;
  }

  #footer{
    background: #319A1F;
    color: white;
    padding-top: 5px;
  }

  #footer > div{
    display: flex;
    ;
  }

  .contacts > div{
    display: flex;
    margin: 5px;
  }
  a{
    color: #fb9638;
    margin-left: 3px;
  }

  @media (max-width: 380px) {
    #footer{
      font-size: 12px;
      line-height: 15px;
    }
  }
</style>