
package com.bytelegend.github.utils.generated;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "columns",
    "image",
    "imageheight",
    "imagewidth",
    "margin",
    "name",
    "spacing",
    "tilecount",
    "tiledversion",
    "tileheight",
    "objectalignment",
    "tiles",
    "tilewidth",
    "type",
    "version"
})
@Generated("jsonschema2pojo")
public class TiledTileset {

    @JsonProperty("columns")
    private Long columns;
    @JsonProperty("image")
    private String image;
    @JsonProperty("imageheight")
    private Long imageheight;
    @JsonProperty("imagewidth")
    private Long imagewidth;
    @JsonProperty("margin")
    private Long margin;
    @JsonProperty("name")
    private String name;
    @JsonProperty("spacing")
    private Long spacing;
    @JsonProperty("tilecount")
    private Long tilecount;
    @JsonProperty("tiledversion")
    private String tiledversion;
    @JsonProperty("tileheight")
    private Long tileheight;
    @JsonProperty("objectalignment")
    private String objectalignment;
    @JsonProperty("tiles")
    private List<TiledTileset.Tile> tiles = new ArrayList<TiledTileset.Tile>();
    @JsonProperty("tilewidth")
    private Long tilewidth;
    @JsonProperty("type")
    private String type;
    @JsonProperty("version")
    private String version;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public TiledTileset() {
    }

    /**
     * 
     * @param image
     * @param margin
     * @param imageheight
     * @param imagewidth
     * @param columns
     * @param type
     * @param version
     * @param tilewidth
     * @param tiledversion
     * @param tileheight
     * @param tiles
     * @param spacing
     * @param name
     * @param objectalignment
     * @param tilecount
     */
    public TiledTileset(Long columns, String image, Long imageheight, Long imagewidth, Long margin, String name, Long spacing, Long tilecount, String tiledversion, Long tileheight, String objectalignment, List<TiledTileset.Tile> tiles, Long tilewidth, String type, String version) {
        super();
        this.columns = columns;
        this.image = image;
        this.imageheight = imageheight;
        this.imagewidth = imagewidth;
        this.margin = margin;
        this.name = name;
        this.spacing = spacing;
        this.tilecount = tilecount;
        this.tiledversion = tiledversion;
        this.tileheight = tileheight;
        this.objectalignment = objectalignment;
        this.tiles = tiles;
        this.tilewidth = tilewidth;
        this.type = type;
        this.version = version;
    }

    @JsonProperty("columns")
    public Long getColumns() {
        return columns;
    }

    @JsonProperty("columns")
    public void setColumns(Long columns) {
        this.columns = columns;
    }

    @JsonProperty("image")
    public String getImage() {
        return image;
    }

    @JsonProperty("image")
    public void setImage(String image) {
        this.image = image;
    }

    @JsonProperty("imageheight")
    public Long getImageheight() {
        return imageheight;
    }

    @JsonProperty("imageheight")
    public void setImageheight(Long imageheight) {
        this.imageheight = imageheight;
    }

    @JsonProperty("imagewidth")
    public Long getImagewidth() {
        return imagewidth;
    }

    @JsonProperty("imagewidth")
    public void setImagewidth(Long imagewidth) {
        this.imagewidth = imagewidth;
    }

    @JsonProperty("margin")
    public Long getMargin() {
        return margin;
    }

    @JsonProperty("margin")
    public void setMargin(Long margin) {
        this.margin = margin;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("spacing")
    public Long getSpacing() {
        return spacing;
    }

    @JsonProperty("spacing")
    public void setSpacing(Long spacing) {
        this.spacing = spacing;
    }

    @JsonProperty("tilecount")
    public Long getTilecount() {
        return tilecount;
    }

    @JsonProperty("tilecount")
    public void setTilecount(Long tilecount) {
        this.tilecount = tilecount;
    }

    @JsonProperty("tiledversion")
    public String getTiledversion() {
        return tiledversion;
    }

    @JsonProperty("tiledversion")
    public void setTiledversion(String tiledversion) {
        this.tiledversion = tiledversion;
    }

    @JsonProperty("tileheight")
    public Long getTileheight() {
        return tileheight;
    }

    @JsonProperty("tileheight")
    public void setTileheight(Long tileheight) {
        this.tileheight = tileheight;
    }

    @JsonProperty("objectalignment")
    public String getObjectalignment() {
        return objectalignment;
    }

    @JsonProperty("objectalignment")
    public void setObjectalignment(String objectalignment) {
        this.objectalignment = objectalignment;
    }

    @JsonProperty("tiles")
    public List<TiledTileset.Tile> getTiles() {
        return tiles;
    }

    @JsonProperty("tiles")
    public void setTiles(List<TiledTileset.Tile> tiles) {
        this.tiles = tiles;
    }

    @JsonProperty("tilewidth")
    public Long getTilewidth() {
        return tilewidth;
    }

    @JsonProperty("tilewidth")
    public void setTilewidth(Long tilewidth) {
        this.tilewidth = tilewidth;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("version")
    public String getVersion() {
        return version;
    }

    @JsonProperty("version")
    public void setVersion(String version) {
        this.version = version;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(TiledTileset.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("columns");
        sb.append('=');
        sb.append(((this.columns == null)?"<null>":this.columns));
        sb.append(',');
        sb.append("image");
        sb.append('=');
        sb.append(((this.image == null)?"<null>":this.image));
        sb.append(',');
        sb.append("imageheight");
        sb.append('=');
        sb.append(((this.imageheight == null)?"<null>":this.imageheight));
        sb.append(',');
        sb.append("imagewidth");
        sb.append('=');
        sb.append(((this.imagewidth == null)?"<null>":this.imagewidth));
        sb.append(',');
        sb.append("margin");
        sb.append('=');
        sb.append(((this.margin == null)?"<null>":this.margin));
        sb.append(',');
        sb.append("name");
        sb.append('=');
        sb.append(((this.name == null)?"<null>":this.name));
        sb.append(',');
        sb.append("spacing");
        sb.append('=');
        sb.append(((this.spacing == null)?"<null>":this.spacing));
        sb.append(',');
        sb.append("tilecount");
        sb.append('=');
        sb.append(((this.tilecount == null)?"<null>":this.tilecount));
        sb.append(',');
        sb.append("tiledversion");
        sb.append('=');
        sb.append(((this.tiledversion == null)?"<null>":this.tiledversion));
        sb.append(',');
        sb.append("tileheight");
        sb.append('=');
        sb.append(((this.tileheight == null)?"<null>":this.tileheight));
        sb.append(',');
        sb.append("objectalignment");
        sb.append('=');
        sb.append(((this.objectalignment == null)?"<null>":this.objectalignment));
        sb.append(',');
        sb.append("tiles");
        sb.append('=');
        sb.append(((this.tiles == null)?"<null>":this.tiles));
        sb.append(',');
        sb.append("tilewidth");
        sb.append('=');
        sb.append(((this.tilewidth == null)?"<null>":this.tilewidth));
        sb.append(',');
        sb.append("type");
        sb.append('=');
        sb.append(((this.type == null)?"<null>":this.type));
        sb.append(',');
        sb.append("version");
        sb.append('=');
        sb.append(((this.version == null)?"<null>":this.version));
        sb.append(',');
        sb.append("additionalProperties");
        sb.append('=');
        sb.append(((this.additionalProperties == null)?"<null>":this.additionalProperties));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result* 31)+((this.image == null)? 0 :this.image.hashCode()));
        result = ((result* 31)+((this.margin == null)? 0 :this.margin.hashCode()));
        result = ((result* 31)+((this.imageheight == null)? 0 :this.imageheight.hashCode()));
        result = ((result* 31)+((this.imagewidth == null)? 0 :this.imagewidth.hashCode()));
        result = ((result* 31)+((this.columns == null)? 0 :this.columns.hashCode()));
        result = ((result* 31)+((this.type == null)? 0 :this.type.hashCode()));
        result = ((result* 31)+((this.version == null)? 0 :this.version.hashCode()));
        result = ((result* 31)+((this.tilewidth == null)? 0 :this.tilewidth.hashCode()));
        result = ((result* 31)+((this.tiledversion == null)? 0 :this.tiledversion.hashCode()));
        result = ((result* 31)+((this.tileheight == null)? 0 :this.tileheight.hashCode()));
        result = ((result* 31)+((this.tiles == null)? 0 :this.tiles.hashCode()));
        result = ((result* 31)+((this.spacing == null)? 0 :this.spacing.hashCode()));
        result = ((result* 31)+((this.name == null)? 0 :this.name.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.objectalignment == null)? 0 :this.objectalignment.hashCode()));
        result = ((result* 31)+((this.tilecount == null)? 0 :this.tilecount.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof TiledTileset) == false) {
            return false;
        }
        TiledTileset rhs = ((TiledTileset) other);
        return (((((((((((((((((this.image == rhs.image)||((this.image!= null)&&this.image.equals(rhs.image)))&&((this.margin == rhs.margin)||((this.margin!= null)&&this.margin.equals(rhs.margin))))&&((this.imageheight == rhs.imageheight)||((this.imageheight!= null)&&this.imageheight.equals(rhs.imageheight))))&&((this.imagewidth == rhs.imagewidth)||((this.imagewidth!= null)&&this.imagewidth.equals(rhs.imagewidth))))&&((this.columns == rhs.columns)||((this.columns!= null)&&this.columns.equals(rhs.columns))))&&((this.type == rhs.type)||((this.type!= null)&&this.type.equals(rhs.type))))&&((this.version == rhs.version)||((this.version!= null)&&this.version.equals(rhs.version))))&&((this.tilewidth == rhs.tilewidth)||((this.tilewidth!= null)&&this.tilewidth.equals(rhs.tilewidth))))&&((this.tiledversion == rhs.tiledversion)||((this.tiledversion!= null)&&this.tiledversion.equals(rhs.tiledversion))))&&((this.tileheight == rhs.tileheight)||((this.tileheight!= null)&&this.tileheight.equals(rhs.tileheight))))&&((this.tiles == rhs.tiles)||((this.tiles!= null)&&this.tiles.equals(rhs.tiles))))&&((this.spacing == rhs.spacing)||((this.spacing!= null)&&this.spacing.equals(rhs.spacing))))&&((this.name == rhs.name)||((this.name!= null)&&this.name.equals(rhs.name))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.objectalignment == rhs.objectalignment)||((this.objectalignment!= null)&&this.objectalignment.equals(rhs.objectalignment))))&&((this.tilecount == rhs.tilecount)||((this.tilecount!= null)&&this.tilecount.equals(rhs.tilecount))));
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
        "duration",
        "tileid"
    })
    @Generated("jsonschema2pojo")
    public static class Animation {

        @JsonProperty("duration")
        private Long duration;
        @JsonProperty("tileid")
        private Long tileid;
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        /**
         * No args constructor for use in serialization
         * 
         */
        public Animation() {
        }

        /**
         * 
         * @param duration
         * @param tileid
         */
        public Animation(Long duration, Long tileid) {
            super();
            this.duration = duration;
            this.tileid = tileid;
        }

        @JsonProperty("duration")
        public Long getDuration() {
            return duration;
        }

        @JsonProperty("duration")
        public void setDuration(Long duration) {
            this.duration = duration;
        }

        @JsonProperty("tileid")
        public Long getTileid() {
            return tileid;
        }

        @JsonProperty("tileid")
        public void setTileid(Long tileid) {
            this.tileid = tileid;
        }

        @JsonAnyGetter
        public Map<String, Object> getAdditionalProperties() {
            return this.additionalProperties;
        }

        @JsonAnySetter
        public void setAdditionalProperty(String name, Object value) {
            this.additionalProperties.put(name, value);
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(TiledTileset.Animation.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
            sb.append("duration");
            sb.append('=');
            sb.append(((this.duration == null)?"<null>":this.duration));
            sb.append(',');
            sb.append("tileid");
            sb.append('=');
            sb.append(((this.tileid == null)?"<null>":this.tileid));
            sb.append(',');
            sb.append("additionalProperties");
            sb.append('=');
            sb.append(((this.additionalProperties == null)?"<null>":this.additionalProperties));
            sb.append(',');
            if (sb.charAt((sb.length()- 1)) == ',') {
                sb.setCharAt((sb.length()- 1), ']');
            } else {
                sb.append(']');
            }
            return sb.toString();
        }

        @Override
        public int hashCode() {
            int result = 1;
            result = ((result* 31)+((this.duration == null)? 0 :this.duration.hashCode()));
            result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
            result = ((result* 31)+((this.tileid == null)? 0 :this.tileid.hashCode()));
            return result;
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }
            if ((other instanceof TiledTileset.Animation) == false) {
                return false;
            }
            TiledTileset.Animation rhs = ((TiledTileset.Animation) other);
            return ((((this.duration == rhs.duration)||((this.duration!= null)&&this.duration.equals(rhs.duration)))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.tileid == rhs.tileid)||((this.tileid!= null)&&this.tileid.equals(rhs.tileid))));
        }

    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
        "animation",
        "id"
    })
    @Generated("jsonschema2pojo")
    public static class Tile {

        @JsonProperty("animation")
        private List<TiledTileset.Animation> animation = new ArrayList<TiledTileset.Animation>();
        @JsonProperty("id")
        private Long id;
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        /**
         * No args constructor for use in serialization
         * 
         */
        public Tile() {
        }

        /**
         * 
         * @param id
         * @param animation
         */
        public Tile(List<TiledTileset.Animation> animation, Long id) {
            super();
            this.animation = animation;
            this.id = id;
        }

        @JsonProperty("animation")
        public List<TiledTileset.Animation> getAnimation() {
            return animation;
        }

        @JsonProperty("animation")
        public void setAnimation(List<TiledTileset.Animation> animation) {
            this.animation = animation;
        }

        @JsonProperty("id")
        public Long getId() {
            return id;
        }

        @JsonProperty("id")
        public void setId(Long id) {
            this.id = id;
        }

        @JsonAnyGetter
        public Map<String, Object> getAdditionalProperties() {
            return this.additionalProperties;
        }

        @JsonAnySetter
        public void setAdditionalProperty(String name, Object value) {
            this.additionalProperties.put(name, value);
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(TiledTileset.Tile.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
            sb.append("animation");
            sb.append('=');
            sb.append(((this.animation == null)?"<null>":this.animation));
            sb.append(',');
            sb.append("id");
            sb.append('=');
            sb.append(((this.id == null)?"<null>":this.id));
            sb.append(',');
            sb.append("additionalProperties");
            sb.append('=');
            sb.append(((this.additionalProperties == null)?"<null>":this.additionalProperties));
            sb.append(',');
            if (sb.charAt((sb.length()- 1)) == ',') {
                sb.setCharAt((sb.length()- 1), ']');
            } else {
                sb.append(']');
            }
            return sb.toString();
        }

        @Override
        public int hashCode() {
            int result = 1;
            result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
            result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
            result = ((result* 31)+((this.animation == null)? 0 :this.animation.hashCode()));
            return result;
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }
            if ((other instanceof TiledTileset.Tile) == false) {
                return false;
            }
            TiledTileset.Tile rhs = ((TiledTileset.Tile) other);
            return ((((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id)))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.animation == rhs.animation)||((this.animation!= null)&&this.animation.equals(rhs.animation))));
        }

    }

}
