<template>
    <div class="timetable-container">
        <div class="onedayblock-container" v-for="(oneDay, index) in daysArray" :key="index">
            <OneDayBlock :info="oneDay" :is-first="index === 0"/>
        </div>
    </div>
</template>

<script>

    import OneDayBlock from "./OneDayBlock";

    export default {
        name: "TimeTable",
        components: {
            OneDayBlock
        },
        computed: {
            daysArray() {
                let data = [];

                this.$store.state.timetable.forEach(item => {
                    if(data.length === 0 || item.date != data[data.length - 1][0].date)
                        data.push([item]);
                    else
                        data[data.length - 1].push(item);
                });
                return data;
            }
        }
    }
</script>

<style scoped>

    .timetable-container{
        display: flex;
        flex-direction: column;
        align-items: center;
        margin-top: 10vh;
        font-family: Roboto;
    }

    .onedayblock-container{
        margin-bottom: 30px;
    }


</style>