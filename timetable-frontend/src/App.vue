<template>
    <div id="app">
        <BaseHeader v-bind:is-group-selected="isGroupSelected" @goToStartPage="clearGroup"/>
        <Message v-if="isGroupSelected"/>
        <TimeTable v-if="isGroupSelected"/>
        <SelectGroup v-else @groupSelected="groupSelected"/>
        <div class="footer_wrapper">
          <BaseFooter v-if="isGroupSelected"/>
        </div>
    </div>
</template>

<script>
    import BaseHeader from "./components/BaseHeader";
    import TimeTable from "./components/TimeTable";
    import SelectGroup from "./components/SelectGroup";
    import axios from 'axios'
    import BaseFooter from "./components/BaseFooter";
    import Message from "./components/Message";

    export default {
        name: 'App',
        components: {
            BaseHeader,
            SelectGroup,
            TimeTable,
            BaseFooter,
            Message
        },
        methods: {
            downloadTimeTable(groupName) {
                axios.get("api/timetable/educationalInstitutions/ranepa_spb/groups/" + encodeURI(groupName))
                    .then(response => {
                        this.$store.commit("setTimeTable", response.data);
                        this.$store.commit("sortTimeTable");
                        this.$store.commit("convertNoteToJson");
                    });
            },

            removeTimeTable(){
              this.$store.commit("clearTimeTable");
            },

            addGroupNameToStorage(groupName){
                localStorage.setItem("groupName", groupName);
            },

            removeGroupNameFromStorage(){
              localStorage.removeItem("groupName");
            },

            groupSelected(groupName){
                this.downloadTimeTable(groupName);
                this.addGroupNameToStorage(groupName);
            },
            clearGroup(){
                this.removeTimeTable();
                this.removeGroupNameFromStorage();
            }
        },
        created() {
            axios.get("api/timetable/educationalInstitutions/ranepa_spb/groups")
                .then(response => {
                    this.$store.commit("setGroupList", response.data);
                });
            if (localStorage.getItem("groupName") != null) {
                this.downloadTimeTable(localStorage.getItem("groupName"));
            }
        },
        computed: {
            isGroupSelected() {
                return this.$store.state.timetable.length > 0;
            }
        }
    }
</script>

<style>

    @font-face {
        font-family: ElMessiri;
        src: url('assets/fonts/ElMessiri-Bold.ttf');
        font-weight: bold;
        font-style: normal;
        font-display: swap;
    }

    @font-face {
        font-family: ElMessiri;
        src: url('assets/fonts/ElMessiri-Regular.ttf');
        font-weight: normal;
        font-style: normal;
        font-display: swap;
    }

    * {
        margin: 0;
        padding: 0;
    }

    body {
        background: #E6F3E4;
    }

    input {
        outline: none;
    }

    .footer_wrapper{
      display: flex;
      flex-direction: column;
      align-content: space-between;
    }

</style>

<style scoped>
    article {
        display: flex;
    }
</style>
