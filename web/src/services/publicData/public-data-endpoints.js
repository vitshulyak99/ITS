
const server='http://localhost:9000';

class PublicDataEndpoints {
    Roles=`${server}/public/api/data/allRoles`;
    Degrees=`${server}/public/api/data/allDegrees`;
    Sections=`${server}/public/api/data/allSections`;
}

export default new PublicDataEndpoints();