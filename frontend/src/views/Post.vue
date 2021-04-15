<template>
  <CRow>
    <CCol col="12" lg="12">
      <CCard>
        <CCardHeader>
          Post id:  {{ $route.params.id }}
        </CCardHeader>
        <CCardBody>
          <CDataTable
            striped
            small
            fixed
            :items="post"
            :fields="fields"
          >
        <template #addrProvince="{item}">
            <td>
                {{item.address.addrProvince}}
            </td>
        </template>
        <template #addrCity="{item}">
            <td>
                {{item.address.addrCity}}
            </td>
        </template>
        <template #addrDistrict="{item}">
            <td>
                {{item.address.addrDistrict}}
            </td>
        </template>
        <template #addrWard="{item}">
            <td>
                {{item.address.addrWard}}
            </td>
        </template>
        <template #project="{item}">
            <td>
                {{item.project.project}}
            </td>
        </template>
                            <template #transaction="{item}">
                                <td>
                                    {{item.transactionType.transaction_type}}
                                </td>
                            </template>
                            <template #property="{item}">
                                <td>
                                    {{item.propertyType.propertyType}}
                                </td>
                            </template>
          </CDataTable>
        </CCardBody>
        <!-- <CCardFooter>
          <CButton color="primary" @click="goBack">Back</CButton>
        </CCardFooter> -->
      </CCard>
    </CCol>
  </CRow>
</template>

// <script>
// import usersData from './UsersData'
// export default {
//   name: 'User',
//   beforeRouteEnter(to, from, next) {
//     next(vm => {
//       vm.usersOpened = from.fullPath.includes('users')
//     })
//   },
//   data () {
//     return {
//       usersOpened: null
//     }
//   },
//   computed: {
//     fields () {
//       return [
//         { key: 'key', label: this.username, _style: 'width:150px'},
//         { key: 'value', label: '', _style: 'width:150px;' }
//       ]
//     },
//     userData () {
//       const id = this.$route.params.id
//       const user = usersData.find((user, index) => index + 1 == id)
//       const userDetails = user ? Object.entries(user) : [['id', 'Not found']]
//       return userDetails.map(([key, value]) => { return { key, value } })
//     },
//     visibleData () {
//       return this.userData.filter(param => param.key !== 'username')
//     },
//     username () {
//       return this.userData.filter(param => param.key === 'username')[0].value
//     }
//   },
//   methods: {
//     goBack() {
//       this.usersOpened ? this.$router.go(-1) : this.$router.push({path: '/posts'})
//     }
//   }
// }
// </script>
<script>
    import axios from "axios";
    export default {
        name: 'Post',
        data() { return {
            fields: [
                { key: 'url'},
                { key: 'price' },
                { key: 'price_unit' },
                { key: 'area' },
                { key: 'addrProvince', label: 'Province' },
                { key: 'addrCity', label: 'City' },
                { key: 'addrDistrict', label: 'District'},
                { key: 'addrWard', label: 'Ward'},
                { key: 'project'},
                { key: 'transaction'},
                { key: 'property'}
            ],
            post: []
        }},
        methods: {
            getPost: function() {
                axios.get("http://localhost:9000/api/v1/post/"+this.$route.params.id)
                .then(function (response) {
                    this.post = [response.data];
                    console.log(this.post[0].address.addrProvince);
                }.bind(this));
            }
        },
        created() {
            this.getPost();
        }
    }
</script>