import Axios from "axios";
import DownlaodEndpoints from "./download-endpoints";

export default new class DownloadService {
    token='';
    constructor(token) {

        this.token = `Bearer_${token}`;
    }


    download(fileId){
       return Axios({
            url: `${DownlaodEndpoints.Report}`,
            method: 'GET',
            responseType: 'blob',
            params:{
                fileId
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

    downloadAll(sectionId,type){
        return Axios({
            url: `${DownlaodEndpoints.Archive}`,
            method: 'GET',
            responseType: 'blob',
            params:{
                sectionId,
                type
            }
            // important
        }).then((response) => {
            const url = window.URL.createObjectURL(new Blob([response.data]));
            const link = document.createElement('a');
            link.href = url;
            link.setAttribute('download', 'file.zip');
            document.body.appendChild(link);
            link.click();
        });
    }

}