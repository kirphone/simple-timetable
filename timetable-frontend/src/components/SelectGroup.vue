<template>
    <div id="main">
        <h2 class="app-name">
            Расписание
        </h2>

        <form v-on:submit="selectGroup">
            <div class="input-container">
                <autocomplete @submit="selectGroup" class="search-input" auto-select placeholder="Введите вашу группу..." :search="search"></autocomplete>
            </div>
        </form>
    </div>
</template>

<script>
    import Autocomplete from '@trevoreyre/autocomplete-vue'

    import '@trevoreyre/autocomplete-vue/dist/style.css'

    export default {
        components: {
          Autocomplete
        },
        name: "SelectGroup",
        data(){
          return{
              groupInput : ""
          }
        },
        methods: {
            selectGroup(result) {
                if (this.groupList.includes(result)) {
                    this.$emit("groupSelected", result);
                }
            },
            search(input) {
                if (input.length < 1) {
                    return []
                }
                return this.groupList.filter(group => {
                    return group.toLowerCase()
                        .includes(input.toLowerCase())
                })
            }
        },
        computed: {
            groupList() {
                return this.$store.state.groupList;
            }
        }
    }
</script>

<style>

    .autocomplete-input::placeholder {
        color: rgba(0, 0, 0, 0.24);
    }

   .autocomplete-input{
       padding: 12px;
       width: 100%;
       height: 100%;
       text-align: center;
       font-family: Roboto;
       font-style: normal;
       font-weight: normal;
       font-size: 3.375vmax;
       line-height: 3.75vmax;
       background: #FFFFFF;
       outline: none;
   }

   .autocomplete{
       width: 800px;
       height: 80px;
       margin-top: 38px;
       display: block;
   }

   @media (max-width: 1000px) {
       .autocomplete {
           max-width: 600px;
       }
   }

   @media (max-width: 700px) {
       .autocomplete {
           width: calc(100% - 52px);
           margin-left: 26px;
           margin-right: 26px;
           height: 65px;
       }
   }

   @media (max-width: 470px) {
       .autocomplete {
           height: 54px;
       }
   }

   @media (max-width: 300px) {
       .autocomplete {
           max-width: 100%;
           margin-left: 26px;
           margin-right: 26px;
           height: 35px;
       }

       .autocomplete-input {
           font-size: 12px;
           line-height: 12px;
       }
   }
</style>

<style scoped>
    #main {
        display: flex;
        flex-direction: column;
        align-items: center;
    }

    .app-name {
        font-family: Ubuntu;
        font-style: normal;
        font-weight: bold;
        font-size: 4.5vmax;
        line-height: 5vmax;
        color: #115C05;
        margin-top: 18vh;
    }

    .input-container {
        display: flex;
        justify-content: center;
    }
</style>