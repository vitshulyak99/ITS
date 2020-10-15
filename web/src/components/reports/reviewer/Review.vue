<template>
    <div class="d-flex flex-column p-1">
        <div class="d-inline-flex mt-2">
            <a href="http://localhost:9000/download/s" class="btn btn-primary btn-link col-2 text-white mr-3" target="_blank">Скачать</a>
            <button class="btn btn-dark " @click="isReview = !isReview">
                {{!isReview?'Оставить отзыв':'Отмена'}}
            </button>

        </div>
        <div v-if="isReview" class="row-2 mt-2 mb-2">
            <textarea class="text-left form-control" v-model="message">
            </textarea>

        <div class=" d-inline-flex col justify-content-center">
            <button class="btn btn-success col-2 mr-2" @click="aprove">Одобрить</button>
            <button class="btn btn-danger col-2 ml-2">Отклонить</button>
        </div>
        </div>
    </div>
</template>

<script>


    import Axios from "axios";

    export default {
        name: "Review",
        props:['report'],
        data(){
            return {
                isReview:false,
                message:''
            }
        },
        methods:{
            aprove(){
                const mymessage = this.message;
                Axios({
                    url: `http://localhost:9000/send`,
                    method: 'GET',
                    params:{
                        message:mymessage
                    }// important
                }).then((response) => {
                   console.log(response.data);
                })
            }
        }
    }
</script>

<style scoped>

</style>