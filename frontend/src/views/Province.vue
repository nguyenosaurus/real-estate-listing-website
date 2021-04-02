<template>
    <div id="province">
        <google-map v-bind:posts="posts"/>
        <table class="table table-striped table-condensed table-bordered">
            <tr>
                <th>price</th>
                <th>price_unit</th>
                <th>area</th>
                <th>transaction_type</th>
                <th>property_type</th>
                <th>addr_province</th>
                <th>addr_city</th>
                <th>addr_district</th>
                <th>addr_ward</th>
                <th>addr_street</th>
                <th>longitude</th>
                <th>latitude</th>
                <th>num_bedrooms</th>
                <th>num_bathrooms</th>
                <th>project</th>
                <th>project_size</th>
                <th>created_date</th>
                <th>expired_date</th>
                <th>num_floors</th>
                <th>floorth</th>
                <th>direction</th>
                <th>legal</th>
                <th>front</th>
                <th>alley</th>
                <th>surrounding</th>
                <th>surrounding characteristics</th>
                <th>position</th>
                <th>internal facility</th>
                <th>post_author</th>
                <th>phone_number</th>
                <th>url</th>
            </tr>
            
            <tr v-for='(row, index) in posts' :key='index'>
                <td>{{row.price}}</td>
                <td>{{row.price_unit}}</td>
                <td>{{row.area}}</td>
                <td>{{row.transactionType.transaction_type}}</td>
                <td>{{row.propertyType.propertyType}}</td>
                <td>{{row.address.addrProvince}}</td>
                <td>{{row.address.addrCity}}</td>
                <td>{{row.address.addrDistrict}}</td>
                <td>{{row.address.addrWard}}</td>
                <td>{{row.address.addr_street}}</td>
                <td>{{row.address.longitude}}</td>
                <td>{{row.address.latitude}}</td>
                <td>{{row.num_bedrooms}}</td>
                <td>{{row.num_bathrooms}}</td>
                <td>{{row.project.project}}</td>
                <td>{{row.project.project_size}}</td>
                <td>{{row.created_date}}</td>
                <td>{{row.expired_date}}</td>
                <td>{{row.num_floors}}</td>
                <td>{{row.floorth}}</td>
                <td>{{row.direction}}</td>
                <td>{{row.legal}}</td>
                <td>{{row.front}}</td>
                <td>{{row.alley}}</td>
                <td>{{row.surrounding}}</td>
                <td>{{row.surrounding_characteristics}}</td>
                <td>{{row.position}}</td>
                <td>{{row.internal_facility}}</td>
                <td>{{row.author.post_author}}</td>
                <td>{{row.author.phone_number}}</td>
                <td>{{row.url}}</td>
            </tr>
        </table>

        <nav>
            <ul class="pagination">
                <li v-if="pagination.current_page > 1">
                    <a href="#" aria-label="Previous"
                       @click.prevent="changePage(pagination.current_page - 1)">
                        <span aria-hidden="true">Prev</span>
                    </a>
                </li>
                <li v-for="page in pagesNumber" v-bind:class="[ page == isActived ? 'active' : '']" :key="page">
                    <a href="#" @click.prevent="changePage(page)">{{ page }}</a>
                </li>
                <li v-if="pagination.current_page < pagination.total">
                    <a href="#" aria-label="Next" @click.prevent="changePage(pagination.current_page + 1)">
                        <span aria-hidden="true">Next</span>
                    </a>
                </li>
            </ul>
        </nav>
    </div>
</template>

<style>
@import url('https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0-alpha/css/bootstrap.css');
table {  
    border-collapse: collapse;  
}  
    .inline{   
        display: inline-block;   
        float: right;   
        margin: 20px 0px;   
    }   
     
    input, button{   
        height: 34px;   
    }     
</style>
<script>
import axios from "axios";
import GoogleMap from "../components/GoogleMap";
export default {
name: 'Province',
components: { GoogleMap },
data() { return {
    province: this.$route.params.province,
    district: this.$route.params.district,
    ward: this.$route.params.ward,
    project: this.$route.query.project,
    property: this.$route.query.property,
    transaction: this.$route.query.transaction,
    posts: [],
    pagination: {
        total: 0, 
        current_page: this.$route.query.page
      },
    offset: 4,
    price: [this.$route.query.priceGT, this.$route.query.priceLT],
    area: [this.$route.query.areaGT, this.$route.query.areaLT]
  }},
  computed: {
        isActived: function () {
            return this.pagination.current_page;
        },
        pagesNumber: function () {
            if (!this.pagination.total) {
                return [];
            }
            var from = this.pagination.current_page - this.offset;
            if (from < 1) {
                from = 1;
            }
            var to = from + (this.offset * 2);
            if (to >= this.pagination.total) {
                to = this.pagination.total;
            }
            var pagesArray = [];
            while (from <= to) {
                pagesArray.push(from);
                from++;
            }
            return pagesArray;
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
          var page = this.pagination.current_page ? this.pagination.current_page - 1 : 0;
          var priceGT = this.price[0] ? this.price[0] : 0;
          var priceLT = this.price[1] ? this.price[1] : 10000000;
          var areaGT = this.area[0] ? this.area[0] : 0;
          var areaLT = this.area[1] ? this.area[1] : 310000;
          
          axios.get("http://localhost:9000/api/v1/posts"+provinceText+districtText+wardText+"?project="+projectText+"&property="+propertyText+"&transaction="+transactionText+"&price.gt="+priceGT+"&price.lt="+priceLT+"&area.gt="+areaGT+"&area.lt="+areaLT+"&page="+page)
          .then(function (response) {
              this.posts = response.data.content;
              this.pagination.total = response.data.totalPages;
              this.pagination.current_page = response.data.number + 1;
          }.bind(this));
      },
      changePage: function (page) {
          var provinceText = (this.province) ? this.province.toLowerCase().replace(/(huyện |thành phố |thị xã |quận |phường |thị trấn |xã |tỉnh )/,'') : "";
          var districtText = (this.district) ? "/"+this.district.toLowerCase().replace(/(huyện |thành phố |thị xã |quận |phường |thị trấn |xã |tỉnh )/,'') : "";
          var wardText = (this.ward) ? "/"+this.ward.toLowerCase().replace(/(huyện |thành phố |thị xã |quận |phường |thị trấn |xã |tỉnh )/,'') : "";
          var projectText = this.project ? this.project : "";
          var propertyText = this.property ? this.property : "";
          var transactionText = this.transaction ? this.transaction : "";
          var priceGT = this.price[0] ? this.price[0] : 0;
          var priceLT = this.price[1] ? this.price[1] : 10000000;
          var areaGT = this.area[0] ? this.area[0] : 0;
          var areaLT = this.area[1] ? this.area[1] : 310000;

          this.$router.push("/"+provinceText+districtText+wardText+"?project="+projectText+"&property="+propertyText+"&transaction="+transactionText+"&price.gt="+priceGT+"&price.lt="+priceLT+"&area.gt="+areaGT+"&area.lt="+areaLT+"&page="+page);
      }
  },
  created() {
      this.getPosts();
  }
};
</script>
