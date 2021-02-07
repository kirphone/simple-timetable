<template>
    <div id="header">
        <div>
            <button @click="goBack" class="back_button">
                <svg v-if="isGroupSelected" class="arrow" width="46" height="30" viewBox="0 0 46 30" fill="none"
                     xmlns="http://www.w3.org/2000/svg">
                    <path d="M0.585785 13.5858C-0.195263 14.3668 -0.195263 15.6332 0.585785 16.4142L13.3137 29.1421C14.0948 29.9232 15.3611 29.9232 16.1421 29.1421C16.9232 28.3611 16.9232 27.0948 16.1421 26.3137L4.82843 15L16.1421 3.68629C16.9232 2.90524 16.9232 1.63891 16.1421 0.857864C15.3611 0.0768156 14.0948 0.0768156 13.3137 0.857864L0.585785 13.5858ZM46 13L2 13V17L46 17V13Z"
                          fill="white"/>
                </svg>
            </button>
        </div>
        <div>
            <h3 id="university">СЗИУ РАНХиГС</h3>
        </div>
        <div class="app-logo-container">
            <svg class="app-logo" width="67" height="64" viewBox="0 0 67 64" fill="none"
                 xmlns="http://www.w3.org/2000/svg">
                <rect x="2.5" y="9.5" width="62" height="52" rx="3.5" stroke="#115C05" stroke-width="5"/>
                <path d="M12 2.5C12 1.11929 13.1193 0 14.5 0C15.8807 0 17 1.11929 17 2.5V6.5C17 7.88071 15.8807 9 14.5 9C13.1193 9 12 7.88071 12 6.5V2.5Z"
                      fill="#115C05"/>
                <path d="M50 2.5C50 1.11929 51.1193 0 52.5 0C53.8807 0 55 1.11929 55 2.5V6.5C55 7.88071 53.8807 9 52.5 9C51.1193 9 50 7.88071 50 6.5V2.5Z"
                      fill="#115C05"/>
                <path d="M8 47C8 45.8954 8.89543 45 10 45H16C17.1046 45 18 45.8954 18 47V51C18 52.1046 17.1046 53 16 53H10C8.89543 53 8 52.1046 8 51V47Z"
                      fill="#115C05"/>
                <path d="M22 36C22 34.8954 22.8954 34 24 34H30C31.1046 34 32 34.8954 32 36V40C32 41.1046 31.1046 42 30 42H24C22.8954 42 22 41.1046 22 40V36Z"
                      fill="#115C05"/>
                <path d="M22 47C22 45.8954 22.8954 45 24 45H30C31.1046 45 32 45.8954 32 47V51C32 52.1046 31.1046 53 30 53H24C22.8954 53 22 52.1046 22 51V47Z"
                      fill="#115C05"/>
                <path d="M35 36C35 34.8954 35.8954 34 37 34H43C44.1046 34 45 34.8954 45 36V40C45 41.1046 44.1046 42 43 42H37C35.8954 42 35 41.1046 35 40V36Z"
                      fill="#115C05"/>
                <path d="M35 47C35 45.8954 35.8954 45 37 45H43C44.1046 45 45 45.8954 45 47V51C45 52.1046 44.1046 53 43 53H37C35.8954 53 35 52.1046 35 51V47Z"
                      fill="#115C05"/>
                <path d="M49 36C49 34.8954 49.8954 34 51 34H57C58.1046 34 59 34.8954 59 36V40C59 41.1046 58.1046 42 57 42H51C49.8954 42 49 41.1046 49 40V36Z"
                      fill="#115C05"/>
                <path d="M22 25C22 23.8954 22.8954 23 24 23H30C31.1046 23 32 23.8954 32 25V29C32 30.1046 31.1046 31 30 31H24C22.8954 31 22 30.1046 22 29V25Z"
                      fill="#115C05"/>
                <path d="M35 25C35 23.8954 35.8954 23 37 23H43C44.1046 23 45 23.8954 45 25V29C45 30.1046 44.1046 31 43 31H37C35.8954 31 35 30.1046 35 29V25Z"
                      fill="#115C05"/>
                <path d="M49 25C49 23.8954 49.8954 23 51 23H57C58.1046 23 59 23.8954 59 25V29C59 30.1046 58.1046 31 57 31H51C49.8954 31 49 30.1046 49 29V25Z"
                      fill="#115C05"/>
                <path d="M49 47C49 45.8954 49.8954 45 51 45H57C58.1046 45 59 45.8954 59 47V51C59 52.1046 58.1046 53 57 53H51C49.8954 53 49 52.1046 49 51V47Z"
                      fill="#115C05"/>
                <path d="M8 36C8 34.8954 8.89543 34 10 34H16C17.1046 34 18 34.8954 18 36V40C18 41.1046 17.1046 42 16 42H10C8.89543 42 8 41.1046 8 40V36Z"
                      fill="#115C05"/>
                <path d="M3 10H63V20H3V10Z" fill="#115C05"/>
                <path d="M46.875 23.5703H47.875V22.5703V16.875V15.875H46.875H19.0781H18.0781V16.875V22.5703V23.5703H19.0781H28.3906V51V52H29.3906H36.4219H37.4219V51V23.5703H46.875Z"
                      fill="#115C05" stroke="white" stroke-width="2"/>
            </svg>
        </div>
    </div>
</template>

<script>
    export default {
        name: "BaseHeader",
        props: {
            isGroupSelected: Boolean
        },
        methods: {
            goBack(){
                this.$emit("goToStartPage");
            }
        }
    }
</script>

<style scoped>

    #header {
        display: flex;
        align-items: center;
        justify-content: space-between;
        height: 9.5vh;
        background: #319A1F;
    }

    .app-logo {
        height: calc(9.5vh - 6px);
        width: calc((9.5vh - 6px) * 67 / 64);
        margin-top: 3px;
        margin-bottom: 3px;
        margin-left: 5px;
    }

    #university {
        color: white;
        font-size: 3vw;
        font-family: ElMessiri, 'Comic Sans MS', fantasy;
        font-weight: normal;
    }

    input {
        max-width: 280px;
        margin-right: 2vw;
    }

    .back_button{
        border: none;
        background: inherit;
        outline: none;
    }

    .arrow {
        margin-right: 3vw;
        height: 4.25vh;
        width: 8.7vh;
    }

    @media (max-width: 800px) {
        #university {
            font-size: 5vw;
            line-height: 7.5vw;
        }
    }

    @media (max-width: 650px) {
        #university {
            font-size: 6.4vw;
            line-height: 9.6vw;
        }
    }

</style>