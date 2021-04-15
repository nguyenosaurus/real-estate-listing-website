<template>
    <div id="province">
        <CRow>
            <CCol sm="4">
                <CSelect
                    label="Province"
                    :options="provinces"
                    :value.sync="province"
                    @update:value="getDistricts"
                />
            </CCol>
            <CCol sm="4">
                <CSelect
                    label="District"
                    :options="districts"
                    :value.sync="district"
                    @update:value="getWards"
                />
            </CCol>
            <CCol sm="4">
                <CSelect
                    label="Ward"
                    :options="wards"
                    :value.sync="ward"
                />
            </CCol>
            <CCol sm="4">
                <CSelect
                    label="Project"
                    :options="projects"
                    :value.sync="project"
                />
            </CCol>
            <CCol sm="4">
                <CSelect
                    label="Transaction"
                    :options="transactions"
                    :value.sync="transaction"
                />
            </CCol>
            <CCol sm="4">
                <CSelect
                    label="Property"
                    :options="properties"
                    :value.sync="property"
                />
            </CCol>
        </CRow>
        <CCardBody>
            <div class="output">Price: {{ price.value }}</div>
            <Slider
            v-model="price.value"
            v-bind="price">
            </Slider>
        </CCardBody>

        <CCardBody>
        <div class="output">Area: {{ area.value }}</div>
        <Slider
        v-model="area.value"
        v-bind="area">
        </Slider>
        </CCardBody>
            <CRow>
                <CCol sm="4">
                    <CButton @click="getPosts" block color="secondary">Search</CButton>
                </CCol>
            </CRow>
        <br/>
        <google-map v-bind:posts="posts"/>
        <CRow>
            <CCol col="12" xl="12">
                <CCard>
                    <CCardBody>
                        <CDataTable
                            hover
                            striped
                            :items="posts"
                            :fields="fields"
                            :items-per-page="10"
                            clickable-rows
                            @row-clicked="rowClicked"
                            border
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
                </CCard>
            </CCol>
        </CRow>

        <CCardBody>
          <CPagination
            :active-page.sync="currentPage"
            :pages="total"
            @update:activePage="getPosts"
          />
          <br>
        </CCardBody>
    </div>
</template>
<style src="@vueform/slider/themes/default.css"></style>
<script>
    import axios from "axios";
    import store from "@/region.js";
    import Slider from '@vueform/slider/dist/slider.vue2.js'
    import GoogleMap from "../components/GoogleMap";
    export default {
        name: 'Province',
        components: { Slider, GoogleMap },
        data() { return {
            provinces: store.provinces,
            districts: [],
            wards: [],
            projects: [],
            transactions: ['bán', 'cho thuê'],
            properties: [],
            fields: [
                { key: 'id'},
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
            province: this.$route.params.province,
            district: this.$route.params.district,
            ward: this.$route.params.ward,
            project: this.$route.query.project,
            property: this.$route.query.property,
            transaction: this.$route.query.transaction,
            posts: [],
            total: 0,
            currentPage: 1,
            price: {
            value: [0, 10000000],
            max: 10000000
            },
            area: {
            value: [0, 310000],
            max: 310000
            }
        }},
        watch: {
            $route: {
                immediate: true,
                handler (route) {
                    if (route.query && route.query.page) {
                    this.currentPage = Number(route.query.page)
                    }
                }
            }
        },
        methods: {
            getPosts: function() {
                var provinceText = (this.province) ? "/"+this.province.toLowerCase().replace(/(huyện |thành phố |thị xã |quận |phường |thị trấn |xã |tỉnh )/,'') : "";
                var districtText = (this.district) ? "/"+this.district.toLowerCase().replace(/(huyện |thành phố |thị xã |quận |phường |thị trấn |xã |tỉnh )/,'') : "";
                var wardText = (this.ward) ? "/"+this.ward.toLowerCase().replace(/(huyện |thành phố |thị xã |quận |phường |thị trấn |xã |tỉnh )/,'') : "";
                var projectText = this.project ? this.project : "";
                var propertyText = this.property ? this.property : "";
                var transactionText = this.transaction ? this.transaction : "";
                var page = this.currentPage ? this.currentPage - 1 : 0;
                var priceGT = this.price[0] ? this.price[0] : 0;
                var priceLT = this.price[1] ? this.price[1] : 10000000;
                var areaGT = this.area[0] ? this.area[0] : 0;
                var areaLT = this.area[1] ? this.area[1] : 310000;
                
                axios.get("http://localhost:9000/api/v1/posts"+provinceText+districtText+wardText+"?project="+projectText+"&property="+propertyText+"&transaction="+transactionText+"&price.gt="+this.price.value[0]+"&price.lt="+this.price.value[1]+"&area.gt="+this.area.value[0]+"&area.lt="+this.area.value[1]+"&page="+page)
                .then(function (response) {
                    this.posts = response.data.content;
                    this.total = response.data.totalPages;
                    this.currentPage = response.data.number + 1;
                }.bind(this));
            },
            getDistricts: function() {
                this.districts = store.districts.filter(
                    district => district.province_name === this.province
                )
            },
            getWards: function() {
                this.wards = store.wards.filter(
                    ward => ward.province_name === this.province && ward.district_name === this.district
                )
            },
            getProjects: function(){
                axios.get('http://localhost:9000/showProject')
                .then(function (response) {
                    this.projects = response.data.map(project => ({ value: project.project}));
                }.bind(this));
            },
            getProperties: function(){
                axios.get('http://localhost:9000/showPropertyType')
                .then(function (response) {
                    this.properties = response.data.map(property => ({ value: property.propertyType}));
                }.bind(this));
            },
            rowClicked (item, index) {
                let route =  this.$router.resolve({path: `posts/${item.id}`})
                window.open(route.href, '_blank');
                // this.$router.push({path: `posts/${item.id}`})
            },
        },
        created() {
            this.getProjects();
            this.getProperties();
            this.getPosts();
        }
    };
</script>