const server = "http://localhost:9000";
const access = "public";
const ReportsEndpoints = {
    ConfereeReportsUpload : `${server}/${access}/conferee/upload`,
    GetSections: `${server}/public/conferee/getSections`,
    ChoiceSections: `${server}/${access}/conferee/choiceSection`,
    GetReports: `${server}/${access}/conferee/getReports`,
    GetReportsReviewer: `${server}/${access}/reviewer/getReports`,
    ReviewerDownload:`${server}/${access}/download`
};

export default ReportsEndpoints;