<template>
  <div>
    <div>
      <!-- <h2>Search and add a pin</h2>
      <label>
        <gmap-autocomplete
          @place_changed="setPlace">
        </gmap-autocomplete>
        <button @click="addMarker">Add</button>
      </label>
      <br/> -->

    </div>
    <br>
    <gmap-map
      :center="center"
      :zoom="12"
      style="width:100%;  height: 400px;"
      @idle="searchPostInMaps"
    >
      <gmap-marker
        :key="index"
        v-for="(m, index) in markers"
        :position="m.position"
        @click="center=m.position"
      ></gmap-marker>
    </gmap-map>
  </div>
</template>

<script>
export default {
  name: "GoogleMap",
  props: ['posts'],
  data() {
    return {
        //mặc định là Montreal
      center: { lat: 45.508, lng: -73.587 },
      markers: [],
      places: [],
      currentPlace: null
    };
  },

  mounted() {
    this.geolocate();
  },
  watch: {
    posts() {
      this.searchPostInMaps();
    }
  },

  methods: {
    // nhận địa điểm thông qua autocomplete component
    setPlace(place) {
      this.currentPlace = place;
    },
    addMarker() {
      if (this.currentPlace) {
        const marker = {
          lat: this.currentPlace.geometry.location.lat(),
          lng: this.currentPlace.geometry.location.lng()
        };
        this.markers.push({ position: marker });
        this.places.push(this.currentPlace);
        this.center = marker;
        this.currentPlace = null;
      }
    },
    searchPostInMaps() {
        // console.log(this.posts);
        var p;
        // this.center = {lat: 10.7896455, lng: 106.7048378};
        this.markers = [];
        var flag = false;
        for (p in this.posts) {
            // console.log(p);
            if (this.posts[p].address.longitude && this.posts[p].address.latitude) {
                if (!flag) {
                    this.center = {lat: this.posts[p].address.latitude ,lng: this.posts[p].address.longitude};
                    flag = true;
                }
                // var marker = {lat: this.posts[p].address.longitude ,lng: this.posts[p].address.latitude};
                this.markers.push({position: {lat: this.posts[p].address.latitude ,lng: this.posts[p].address.longitude}});
            }
        }
        console.log(this.markers);
    },
    geolocate() {
        navigator.geolocation.getCurrentPosition(position => {
          this.center = {
            lat: position.coords.latitude,
            lng: position.coords.longitude
          };
        });
    }
  }
};
</script>