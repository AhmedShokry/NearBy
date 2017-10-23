package com.shokry.nearby.Models;

import java.util.List;

/**
 * Created by shokry on 10/20/2017.
 */

public class ForsquareResponseModel {

    private MetaEntity meta;
    private ResponseEntity response;

    public void setMeta(MetaEntity meta) {
        this.meta = meta;
    }

    public void setResponse(ResponseEntity response) {
        this.response = response;
    }

    public MetaEntity getMeta() {
        return meta;
    }

    public ResponseEntity getResponse() {
        return response;
    }

    public static class MetaEntity {
        /**
         * code : 200
         * requestId : 59ea2076351e3d0f93c2b7b3
         */

        private int code;
        private String requestId;

        public void setCode(int code) {
            this.code = code;
        }

        public void setRequestId(String requestId) {
            this.requestId = requestId;
        }

        public int getCode() {
            return code;
        }

        public String getRequestId() {
            return requestId;
        }
    }

    public static class ResponseEntity {

        private boolean confident;
        private List<VenuesEntity> venues;

        public void setConfident(boolean confident) {
            this.confident = confident;
        }

        public void setVenues(List<VenuesEntity> venues) {
            this.venues = venues;
        }

        public boolean getConfident() {
            return confident;
        }

        public List<VenuesEntity> getVenues() {
            return venues;
        }

        public static class VenuesEntity {

            private String id;
            private String name;
            private ContactEntity contact;
            private LocationEntity location;
            private boolean verified;
            private StatsEntity stats;
            private String url;
            private boolean venueRatingBlacklisted;
            private BeenHereEntity beenHere;
            private SpecialsEntity specials;
            private HereNowEntity hereNow;
            private String referralId;
            private boolean hasPerk;
            private List<CategoriesEntity> categories;
            private List<VenueChainsEntity> venueChains;

            public void setId(String id) {
                this.id = id;
            }

            public void setName(String name) {
                this.name = name;
            }

            public void setContact(ContactEntity contact) {
                this.contact = contact;
            }

            public void setLocation(LocationEntity location) {
                this.location = location;
            }

            public void setVerified(boolean verified) {
                this.verified = verified;
            }

            public void setStats(StatsEntity stats) {
                this.stats = stats;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public void setVenueRatingBlacklisted(boolean venueRatingBlacklisted) {
                this.venueRatingBlacklisted = venueRatingBlacklisted;
            }

            public void setBeenHere(BeenHereEntity beenHere) {
                this.beenHere = beenHere;
            }

            public void setSpecials(SpecialsEntity specials) {
                this.specials = specials;
            }

            public void setHereNow(HereNowEntity hereNow) {
                this.hereNow = hereNow;
            }

            public void setReferralId(String referralId) {
                this.referralId = referralId;
            }

            public void setHasPerk(boolean hasPerk) {
                this.hasPerk = hasPerk;
            }

            public void setCategories(List<CategoriesEntity> categories) {
                this.categories = categories;
            }

            public void setVenueChains(List<VenueChainsEntity> venueChains) {
                this.venueChains = venueChains;
            }

            public String getId() {
                return id;
            }

            public String getName() {
                return name;
            }

            public ContactEntity getContact() {
                return contact;
            }

            public LocationEntity getLocation() {
                return location;
            }

            public boolean getVerified() {
                return verified;
            }

            public StatsEntity getStats() {
                return stats;
            }

            public String getUrl() {
                return url;
            }

            public boolean getVenueRatingBlacklisted() {
                return venueRatingBlacklisted;
            }

            public BeenHereEntity getBeenHere() {
                return beenHere;
            }

            public SpecialsEntity getSpecials() {
                return specials;
            }

            public HereNowEntity getHereNow() {
                return hereNow;
            }

            public String getReferralId() {
                return referralId;
            }

            public boolean getHasPerk() {
                return hasPerk;
            }

            public List<CategoriesEntity> getCategories() {
                return categories;
            }

            public List<VenueChainsEntity> getVenueChains() {
                return venueChains;
            }

            public static class ContactEntity {
                /**
                 * phone : 7183301234
                 * formattedPhone : (718) 330-1234
                 * twitter : nyctsubwayscoop
                 */

                private String phone;
                private String formattedPhone;
                private String twitter;

                public void setPhone(String phone) {
                    this.phone = phone;
                }

                public void setFormattedPhone(String formattedPhone) {
                    this.formattedPhone = formattedPhone;
                }

                public void setTwitter(String twitter) {
                    this.twitter = twitter;
                }

                public String getPhone() {
                    return phone;
                }

                public String getFormattedPhone() {
                    return formattedPhone;
                }

                public String getTwitter() {
                    return twitter;
                }
            }

            public static class LocationEntity {
                /**
                 * address : Broadway
                 * crossStreet : at W 34th St
                 * lat : 40.74989749590768
                 * lng : -73.98799879606251
                 * distance : 451
                 * postalCode : 10018
                 * cc : US
                 * city : نيويورك
                 * state : NY
                 * country : الولايات المتحدة الأمريكية
                 * formattedAddress : ["Broadway (at W 34th St)","نيويورك, NY 10018","الولايات المتحدة الأمريكية"]
                 */

                private String address;
                private String crossStreet;
                private double lat;
                private double lng;
                private int distance;
                private String postalCode;
                private String cc;
                private String city;
                private String state;
                private String country;
                private List<String> formattedAddress;

                public void setAddress(String address) {
                    this.address = address;
                }

                public void setCrossStreet(String crossStreet) {
                    this.crossStreet = crossStreet;
                }

                public void setLat(double lat) {
                    this.lat = lat;
                }

                public void setLng(double lng) {
                    this.lng = lng;
                }

                public void setDistance(int distance) {
                    this.distance = distance;
                }

                public void setPostalCode(String postalCode) {
                    this.postalCode = postalCode;
                }

                public void setCc(String cc) {
                    this.cc = cc;
                }

                public void setCity(String city) {
                    this.city = city;
                }

                public void setState(String state) {
                    this.state = state;
                }

                public void setCountry(String country) {
                    this.country = country;
                }

                public void setFormattedAddress(List<String> formattedAddress) {
                    this.formattedAddress = formattedAddress;
                }

                public String getAddress() {
                    return address;
                }

                public String getCrossStreet() {
                    return crossStreet;
                }

                public double getLat() {
                    return lat;
                }

                public double getLng() {
                    return lng;
                }

                public int getDistance() {
                    return distance;
                }

                public String getPostalCode() {
                    return postalCode;
                }

                public String getCc() {
                    return cc;
                }

                public String getCity() {
                    return city;
                }

                public String getState() {
                    return state;
                }

                public String getCountry() {
                    return country;
                }

                public List<String> getFormattedAddress() {
                    return formattedAddress;
                }
            }

            public static class StatsEntity {
                /**
                 * checkinsCount : 68419
                 * usersCount : 10774
                 * tipCount : 63
                 */

                private int checkinsCount;
                private int usersCount;
                private int tipCount;

                public void setCheckinsCount(int checkinsCount) {
                    this.checkinsCount = checkinsCount;
                }

                public void setUsersCount(int usersCount) {
                    this.usersCount = usersCount;
                }

                public void setTipCount(int tipCount) {
                    this.tipCount = tipCount;
                }

                public int getCheckinsCount() {
                    return checkinsCount;
                }

                public int getUsersCount() {
                    return usersCount;
                }

                public int getTipCount() {
                    return tipCount;
                }
            }

            public static class BeenHereEntity {
                /**
                 * lastCheckinExpiredAt : 0
                 */

                private int lastCheckinExpiredAt;

                public void setLastCheckinExpiredAt(int lastCheckinExpiredAt) {
                    this.lastCheckinExpiredAt = lastCheckinExpiredAt;
                }

                public int getLastCheckinExpiredAt() {
                    return lastCheckinExpiredAt;
                }
            }

            public static class SpecialsEntity {
                /**
                 * count : 0
                 */

                private int count;

                public void setCount(int count) {
                    this.count = count;
                }

                public int getCount() {
                    return count;
                }
            }

            public static class HereNowEntity {
                /**
                 * count : 2
                 * summary : 2 people are here
                 * groups : [{"type":"others","name":"Other people here","count":2}]
                 */

                private int count;
                private String summary;
                private List<GroupsEntity> groups;

                public void setCount(int count) {
                    this.count = count;
                }

                public void setSummary(String summary) {
                    this.summary = summary;
                }

                public void setGroups(List<GroupsEntity> groups) {
                    this.groups = groups;
                }

                public int getCount() {
                    return count;
                }

                public String getSummary() {
                    return summary;
                }

                public List<GroupsEntity> getGroups() {
                    return groups;
                }

                public static class GroupsEntity {
                    /**
                     * type : others
                     * name : Other people here
                     * count : 2
                     */

                    private String type;
                    private String name;
                    private int count;

                    public void setType(String type) {
                        this.type = type;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                    public void setCount(int count) {
                        this.count = count;
                    }

                    public String getType() {
                        return type;
                    }

                    public String getName() {
                        return name;
                    }

                    public int getCount() {
                        return count;
                    }
                }
            }

            public static class CategoriesEntity {
                /**
                 * id : 4bf58dd8d48988d1fd931735
                 * name : Metro Station
                 * pluralName : Metro Stations
                 * shortName : Metro
                 * icon : {"prefix":"https://ss3.4sqi.net/img/categories_v2/travel/subway_","suffix":".png"}
                 * primary : true
                 */

                private String id;
                private String name;
                private String pluralName;
                private String shortName;
                private IconEntity icon;
                private boolean primary;

                public void setId(String id) {
                    this.id = id;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public void setPluralName(String pluralName) {
                    this.pluralName = pluralName;
                }

                public void setShortName(String shortName) {
                    this.shortName = shortName;
                }

                public void setIcon(IconEntity icon) {
                    this.icon = icon;
                }

                public void setPrimary(boolean primary) {
                    this.primary = primary;
                }

                public String getId() {
                    return id;
                }

                public String getName() {
                    return name;
                }

                public String getPluralName() {
                    return pluralName;
                }

                public String getShortName() {
                    return shortName;
                }

                public IconEntity getIcon() {
                    return icon;
                }

                public boolean getPrimary() {
                    return primary;
                }

                public static class IconEntity {
                    /**
                     * prefix : https://ss3.4sqi.net/img/categories_v2/travel/subway_
                     * suffix : .png
                     */

                    private String prefix;
                    private String suffix;

                    public void setPrefix(String prefix) {
                        this.prefix = prefix;
                    }

                    public void setSuffix(String suffix) {
                        this.suffix = suffix;
                    }

                    public String getPrefix() {
                        return prefix;
                    }

                    public String getSuffix() {
                        return suffix;
                    }
                }
            }

            public static class VenueChainsEntity {
                /**
                 * id : 56ce1f68498ee907d9ec0318
                 */

                private String id;

                public void setId(String id) {
                    this.id = id;
                }

                public String getId() {
                    return id;
                }
            }
        }
    }
}
