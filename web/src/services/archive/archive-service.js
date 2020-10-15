import AuthorizedService from "../authorized-service";
import Axios from "axios";


export default class ArchiveService extends AuthorizedService{

    uploadFiles(formData){
        return  Axios.post("http://localhost:9000/admin/uploadCollection",formData,
            {
                headers:{
                    'Content-Type': 'multipart/form-data',
                    Authorization: this.token
                }
            })
            .then(response => {

                console.log(response.data);

                return response.data;
            })
            .catch(e=> {
                console.log(e);
                throw e;
            })
    }

    download(id){
        return Axios({
            url: `${"http://localhost:9000/public/archive/download"}`,
            method: 'POST',
            responseType: 'blob',
            params:{
                id
            }// important
        }).then((response) => {
            const url = window.URL.createObjectURL(new Blob([response.data]));
            const link = document.createElement('a');
            link.href = url;
            link.setAttribute('download', 'file.pdf');
            document.body.appendChild(link);
            link.click();
        });
    }

    all(){
        return Axios({
            url: `${"http://localhost:9000/public/archive/all"}`,
            method: 'GET',

           // important
        }).then((response) => {
            console.log( response);
          return response.data;
        });
    }

    remove(id){
        return Axios({
            url: `${"http://localhost:9000/admin/removeCollection"}`,
            method: 'GET',
            headers: {
                Authorization: this.token
            },
            params: {id}
            // important
        }).then((response) => {
            console.log( response);
            return response.data;
        });
    }
}