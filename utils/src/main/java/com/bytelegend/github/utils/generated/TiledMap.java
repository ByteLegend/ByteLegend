
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
    "compressionlevel",
    "height",
    "infinite",
    "layers",
    "nextlayerid",
    "nextobjectid",
    "orientation",
    "renderorder",
    "tiledversion",
    "tileheight",
    "tilesets",
    "tilewidth",
    "type",
    "version",
    "width"
})
@Generated("jsonschema2pojo")
public class TiledMap {

    @JsonProperty("compressionlevel")
    private Long compressionlevel;
    @JsonProperty("height")
    private Long height;
    @JsonProperty("infinite")
    private Boolean infinite;
    @JsonProperty("layers")
    private List<TiledMap.Layer> layers = new ArrayList<TiledMap.Layer>();
    @JsonProperty("nextlayerid")
    private Long nextlayerid;
    @JsonProperty("nextobjectid")
    private Long nextobjectid;
    @JsonProperty("orientation")
    private String orientation;
    @JsonProperty("renderorder")
    private String renderorder;
    @JsonProperty("tiledversion")
    private String tiledversion;
    @JsonProperty("tileheight")
    private Long tileheight;
    @JsonProperty("tilesets")
    private List<TiledMap.Tileset> tilesets = new ArrayList<TiledMap.Tileset>();
    @JsonProperty("tilewidth")
    private Long tilewidth;
    @JsonProperty("type")
    private String type;
    @JsonProperty("version")
    private String version;
    @JsonProperty("width")
    private Long width;
    @JsonIgnore
    private Map<String, java.lang.Object> additionalProperties = new HashMap<String, java.lang.Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public TiledMap() {
    }

    /**
     * 
     * @param orientation
     * @param nextlayerid
     * @param renderorder
     * @param infinite
     * @param type
     * @param version
     * @param nextobjectid
     * @param tilewidth
     * @param tiledversion
     * @param tileheight
     * @param layers
     * @param tilesets
     * @param width
     * @param compressionlevel
     * @param height
     */
    public TiledMap(Long compressionlevel, Long height, Boolean infinite, List<TiledMap.Layer> layers, Long nextlayerid, Long nextobjectid, String orientation, String renderorder, String tiledversion, Long tileheight, List<TiledMap.Tileset> tilesets, Long tilewidth, String type, String version, Long width) {
        super();
        this.compressionlevel = compressionlevel;
        this.height = height;
        this.infinite = infinite;
        this.layers = layers;
        this.nextlayerid = nextlayerid;
        this.nextobjectid = nextobjectid;
        this.orientation = orientation;
        this.renderorder = renderorder;
        this.tiledversion = tiledversion;
        this.tileheight = tileheight;
        this.tilesets = tilesets;
        this.tilewidth = tilewidth;
        this.type = type;
        this.version = version;
        this.width = width;
    }

    @JsonProperty("compressionlevel")
    public Long getCompressionlevel() {
        return compressionlevel;
    }

    @JsonProperty("compressionlevel")
    public void setCompressionlevel(Long compressionlevel) {
        this.compressionlevel = compressionlevel;
    }

    @JsonProperty("height")
    public Long getHeight() {
        return height;
    }

    @JsonProperty("height")
    public void setHeight(Long height) {
        this.height = height;
    }

    @JsonProperty("infinite")
    public Boolean getInfinite() {
        return infinite;
    }

    @JsonProperty("infinite")
    public void setInfinite(Boolean infinite) {
        this.infinite = infinite;
    }

    @JsonProperty("layers")
    public List<TiledMap.Layer> getLayers() {
        return layers;
    }

    @JsonProperty("layers")
    public void setLayers(List<TiledMap.Layer> layers) {
        this.layers = layers;
    }

    @JsonProperty("nextlayerid")
    public Long getNextlayerid() {
        return nextlayerid;
    }

    @JsonProperty("nextlayerid")
    public void setNextlayerid(Long nextlayerid) {
        this.nextlayerid = nextlayerid;
    }

    @JsonProperty("nextobjectid")
    public Long getNextobjectid() {
        return nextobjectid;
    }

    @JsonProperty("nextobjectid")
    public void setNextobjectid(Long nextobjectid) {
        this.nextobjectid = nextobjectid;
    }

    @JsonProperty("orientation")
    public String getOrientation() {
        return orientation;
    }

    @JsonProperty("orientation")
    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    @JsonProperty("renderorder")
    public String getRenderorder() {
        return renderorder;
    }

    @JsonProperty("renderorder")
    public void setRenderorder(String renderorder) {
        this.renderorder = renderorder;
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

    @JsonProperty("tilesets")
    public List<TiledMap.Tileset> getTilesets() {
        return tilesets;
    }

    @JsonProperty("tilesets")
    public void setTilesets(List<TiledMap.Tileset> tilesets) {
        this.tilesets = tilesets;
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

    @JsonProperty("width")
    public Long getWidth() {
        return width;
    }

    @JsonProperty("width")
    public void setWidth(Long width) {
        this.width = width;
    }

    @JsonAnyGetter
    public Map<String, java.lang.Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, java.lang.Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(TiledMap.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("compressionlevel");
        sb.append('=');
        sb.append(((this.compressionlevel == null)?"<null>":this.compressionlevel));
        sb.append(',');
        sb.append("height");
        sb.append('=');
        sb.append(((this.height == null)?"<null>":this.height));
        sb.append(',');
        sb.append("infinite");
        sb.append('=');
        sb.append(((this.infinite == null)?"<null>":this.infinite));
        sb.append(',');
        sb.append("layers");
        sb.append('=');
        sb.append(((this.layers == null)?"<null>":this.layers));
        sb.append(',');
        sb.append("nextlayerid");
        sb.append('=');
        sb.append(((this.nextlayerid == null)?"<null>":this.nextlayerid));
        sb.append(',');
        sb.append("nextobjectid");
        sb.append('=');
        sb.append(((this.nextobjectid == null)?"<null>":this.nextobjectid));
        sb.append(',');
        sb.append("orientation");
        sb.append('=');
        sb.append(((this.orientation == null)?"<null>":this.orientation));
        sb.append(',');
        sb.append("renderorder");
        sb.append('=');
        sb.append(((this.renderorder == null)?"<null>":this.renderorder));
        sb.append(',');
        sb.append("tiledversion");
        sb.append('=');
        sb.append(((this.tiledversion == null)?"<null>":this.tiledversion));
        sb.append(',');
        sb.append("tileheight");
        sb.append('=');
        sb.append(((this.tileheight == null)?"<null>":this.tileheight));
        sb.append(',');
        sb.append("tilesets");
        sb.append('=');
        sb.append(((this.tilesets == null)?"<null>":this.tilesets));
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
        sb.append("width");
        sb.append('=');
        sb.append(((this.width == null)?"<null>":this.width));
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
        result = ((result* 31)+((this.orientation == null)? 0 :this.orientation.hashCode()));
        result = ((result* 31)+((this.nextlayerid == null)? 0 :this.nextlayerid.hashCode()));
        result = ((result* 31)+((this.renderorder == null)? 0 :this.renderorder.hashCode()));
        result = ((result* 31)+((this.infinite == null)? 0 :this.infinite.hashCode()));
        result = ((result* 31)+((this.type == null)? 0 :this.type.hashCode()));
        result = ((result* 31)+((this.version == null)? 0 :this.version.hashCode()));
        result = ((result* 31)+((this.nextobjectid == null)? 0 :this.nextobjectid.hashCode()));
        result = ((result* 31)+((this.tilewidth == null)? 0 :this.tilewidth.hashCode()));
        result = ((result* 31)+((this.tiledversion == null)? 0 :this.tiledversion.hashCode()));
        result = ((result* 31)+((this.tileheight == null)? 0 :this.tileheight.hashCode()));
        result = ((result* 31)+((this.layers == null)? 0 :this.layers.hashCode()));
        result = ((result* 31)+((this.tilesets == null)? 0 :this.tilesets.hashCode()));
        result = ((result* 31)+((this.width == null)? 0 :this.width.hashCode()));
        result = ((result* 31)+((this.compressionlevel == null)? 0 :this.compressionlevel.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.height == null)? 0 :this.height.hashCode()));
        return result;
    }

    @Override
    public boolean equals(java.lang.Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof TiledMap) == false) {
            return false;
        }
        TiledMap rhs = ((TiledMap) other);
        return (((((((((((((((((this.orientation == rhs.orientation)||((this.orientation!= null)&&this.orientation.equals(rhs.orientation)))&&((this.nextlayerid == rhs.nextlayerid)||((this.nextlayerid!= null)&&this.nextlayerid.equals(rhs.nextlayerid))))&&((this.renderorder == rhs.renderorder)||((this.renderorder!= null)&&this.renderorder.equals(rhs.renderorder))))&&((this.infinite == rhs.infinite)||((this.infinite!= null)&&this.infinite.equals(rhs.infinite))))&&((this.type == rhs.type)||((this.type!= null)&&this.type.equals(rhs.type))))&&((this.version == rhs.version)||((this.version!= null)&&this.version.equals(rhs.version))))&&((this.nextobjectid == rhs.nextobjectid)||((this.nextobjectid!= null)&&this.nextobjectid.equals(rhs.nextobjectid))))&&((this.tilewidth == rhs.tilewidth)||((this.tilewidth!= null)&&this.tilewidth.equals(rhs.tilewidth))))&&((this.tiledversion == rhs.tiledversion)||((this.tiledversion!= null)&&this.tiledversion.equals(rhs.tiledversion))))&&((this.tileheight == rhs.tileheight)||((this.tileheight!= null)&&this.tileheight.equals(rhs.tileheight))))&&((this.layers == rhs.layers)||((this.layers!= null)&&this.layers.equals(rhs.layers))))&&((this.tilesets == rhs.tilesets)||((this.tilesets!= null)&&this.tilesets.equals(rhs.tilesets))))&&((this.width == rhs.width)||((this.width!= null)&&this.width.equals(rhs.width))))&&((this.compressionlevel == rhs.compressionlevel)||((this.compressionlevel!= null)&&this.compressionlevel.equals(rhs.compressionlevel))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.height == rhs.height)||((this.height!= null)&&this.height.equals(rhs.height))));
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
        "data",
        "height",
        "id",
        "name",
        "opacity",
        "type",
        "visible",
        "width",
        "x",
        "y",
        "layers",
        "draworder",
        "objects"
    })
    @Generated("jsonschema2pojo")
    public static class Layer {

        @JsonProperty("data")
        private List<Long> data = new ArrayList<Long>();
        @JsonProperty("height")
        private Long height;
        @JsonProperty("id")
        private Long id;
        @JsonProperty("name")
        private String name;
        @JsonProperty("opacity")
        private Long opacity;
        @JsonProperty("type")
        private String type;
        @JsonProperty("visible")
        private Boolean visible;
        @JsonProperty("width")
        private Long width;
        @JsonProperty("x")
        private Long x;
        @JsonProperty("y")
        private Long y;
        @JsonProperty("layers")
        private List<TiledMap.Layer2> layers = new ArrayList<TiledMap.Layer2>();
        @JsonProperty("draworder")
        private String draworder;
        @JsonProperty("objects")
        private List<TiledMap.Object> objects = new ArrayList<TiledMap.Object>();
        @JsonIgnore
        private Map<String, java.lang.Object> additionalProperties = new HashMap<String, java.lang.Object>();

        /**
         * No args constructor for use in serialization
         * 
         */
        public Layer() {
        }

        /**
         * 
         * @param visible
         * @param data
         * @param objects
         * @param draworder
         * @param type
         * @param name
         * @param width
         * @param x
         * @param layers
         * @param y
         * @param id
         * @param opacity
         * @param height
         */
        public Layer(List<Long> data, Long height, Long id, String name, Long opacity, String type, Boolean visible, Long width, Long x, Long y, List<TiledMap.Layer2> layers, String draworder, List<TiledMap.Object> objects) {
            super();
            this.data = data;
            this.height = height;
            this.id = id;
            this.name = name;
            this.opacity = opacity;
            this.type = type;
            this.visible = visible;
            this.width = width;
            this.x = x;
            this.y = y;
            this.layers = layers;
            this.draworder = draworder;
            this.objects = objects;
        }

        @JsonProperty("data")
        public List<Long> getData() {
            return data;
        }

        @JsonProperty("data")
        public void setData(List<Long> data) {
            this.data = data;
        }

        @JsonProperty("height")
        public Long getHeight() {
            return height;
        }

        @JsonProperty("height")
        public void setHeight(Long height) {
            this.height = height;
        }

        @JsonProperty("id")
        public Long getId() {
            return id;
        }

        @JsonProperty("id")
        public void setId(Long id) {
            this.id = id;
        }

        @JsonProperty("name")
        public String getName() {
            return name;
        }

        @JsonProperty("name")
        public void setName(String name) {
            this.name = name;
        }

        @JsonProperty("opacity")
        public Long getOpacity() {
            return opacity;
        }

        @JsonProperty("opacity")
        public void setOpacity(Long opacity) {
            this.opacity = opacity;
        }

        @JsonProperty("type")
        public String getType() {
            return type;
        }

        @JsonProperty("type")
        public void setType(String type) {
            this.type = type;
        }

        @JsonProperty("visible")
        public Boolean getVisible() {
            return visible;
        }

        @JsonProperty("visible")
        public void setVisible(Boolean visible) {
            this.visible = visible;
        }

        @JsonProperty("width")
        public Long getWidth() {
            return width;
        }

        @JsonProperty("width")
        public void setWidth(Long width) {
            this.width = width;
        }

        @JsonProperty("x")
        public Long getX() {
            return x;
        }

        @JsonProperty("x")
        public void setX(Long x) {
            this.x = x;
        }

        @JsonProperty("y")
        public Long getY() {
            return y;
        }

        @JsonProperty("y")
        public void setY(Long y) {
            this.y = y;
        }

        @JsonProperty("layers")
        public List<TiledMap.Layer2> getLayers() {
            return layers;
        }

        @JsonProperty("layers")
        public void setLayers(List<TiledMap.Layer2> layers) {
            this.layers = layers;
        }

        @JsonProperty("draworder")
        public String getDraworder() {
            return draworder;
        }

        @JsonProperty("draworder")
        public void setDraworder(String draworder) {
            this.draworder = draworder;
        }

        @JsonProperty("objects")
        public List<TiledMap.Object> getObjects() {
            return objects;
        }

        @JsonProperty("objects")
        public void setObjects(List<TiledMap.Object> objects) {
            this.objects = objects;
        }

        @JsonAnyGetter
        public Map<String, java.lang.Object> getAdditionalProperties() {
            return this.additionalProperties;
        }

        @JsonAnySetter
        public void setAdditionalProperty(String name, java.lang.Object value) {
            this.additionalProperties.put(name, value);
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(TiledMap.Layer.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
            sb.append("data");
            sb.append('=');
            sb.append(((this.data == null)?"<null>":this.data));
            sb.append(',');
            sb.append("height");
            sb.append('=');
            sb.append(((this.height == null)?"<null>":this.height));
            sb.append(',');
            sb.append("id");
            sb.append('=');
            sb.append(((this.id == null)?"<null>":this.id));
            sb.append(',');
            sb.append("name");
            sb.append('=');
            sb.append(((this.name == null)?"<null>":this.name));
            sb.append(',');
            sb.append("opacity");
            sb.append('=');
            sb.append(((this.opacity == null)?"<null>":this.opacity));
            sb.append(',');
            sb.append("type");
            sb.append('=');
            sb.append(((this.type == null)?"<null>":this.type));
            sb.append(',');
            sb.append("visible");
            sb.append('=');
            sb.append(((this.visible == null)?"<null>":this.visible));
            sb.append(',');
            sb.append("width");
            sb.append('=');
            sb.append(((this.width == null)?"<null>":this.width));
            sb.append(',');
            sb.append("x");
            sb.append('=');
            sb.append(((this.x == null)?"<null>":this.x));
            sb.append(',');
            sb.append("y");
            sb.append('=');
            sb.append(((this.y == null)?"<null>":this.y));
            sb.append(',');
            sb.append("layers");
            sb.append('=');
            sb.append(((this.layers == null)?"<null>":this.layers));
            sb.append(',');
            sb.append("draworder");
            sb.append('=');
            sb.append(((this.draworder == null)?"<null>":this.draworder));
            sb.append(',');
            sb.append("objects");
            sb.append('=');
            sb.append(((this.objects == null)?"<null>":this.objects));
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
            result = ((result* 31)+((this.visible == null)? 0 :this.visible.hashCode()));
            result = ((result* 31)+((this.data == null)? 0 :this.data.hashCode()));
            result = ((result* 31)+((this.objects == null)? 0 :this.objects.hashCode()));
            result = ((result* 31)+((this.draworder == null)? 0 :this.draworder.hashCode()));
            result = ((result* 31)+((this.type == null)? 0 :this.type.hashCode()));
            result = ((result* 31)+((this.name == null)? 0 :this.name.hashCode()));
            result = ((result* 31)+((this.width == null)? 0 :this.width.hashCode()));
            result = ((result* 31)+((this.x == null)? 0 :this.x.hashCode()));
            result = ((result* 31)+((this.layers == null)? 0 :this.layers.hashCode()));
            result = ((result* 31)+((this.y == null)? 0 :this.y.hashCode()));
            result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
            result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
            result = ((result* 31)+((this.opacity == null)? 0 :this.opacity.hashCode()));
            result = ((result* 31)+((this.height == null)? 0 :this.height.hashCode()));
            return result;
        }

        @Override
        public boolean equals(java.lang.Object other) {
            if (other == this) {
                return true;
            }
            if ((other instanceof TiledMap.Layer) == false) {
                return false;
            }
            TiledMap.Layer rhs = ((TiledMap.Layer) other);
            return (((((((((((((((this.visible == rhs.visible)||((this.visible!= null)&&this.visible.equals(rhs.visible)))&&((this.data == rhs.data)||((this.data!= null)&&this.data.equals(rhs.data))))&&((this.objects == rhs.objects)||((this.objects!= null)&&this.objects.equals(rhs.objects))))&&((this.draworder == rhs.draworder)||((this.draworder!= null)&&this.draworder.equals(rhs.draworder))))&&((this.type == rhs.type)||((this.type!= null)&&this.type.equals(rhs.type))))&&((this.name == rhs.name)||((this.name!= null)&&this.name.equals(rhs.name))))&&((this.width == rhs.width)||((this.width!= null)&&this.width.equals(rhs.width))))&&((this.x == rhs.x)||((this.x!= null)&&this.x.equals(rhs.x))))&&((this.layers == rhs.layers)||((this.layers!= null)&&this.layers.equals(rhs.layers))))&&((this.y == rhs.y)||((this.y!= null)&&this.y.equals(rhs.y))))&&((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.opacity == rhs.opacity)||((this.opacity!= null)&&this.opacity.equals(rhs.opacity))))&&((this.height == rhs.height)||((this.height!= null)&&this.height.equals(rhs.height))));
        }

    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
        "data",
        "height",
        "id",
        "name",
        "opacity",
        "type",
        "visible",
        "width",
        "x",
        "y"
    })
    @Generated("jsonschema2pojo")
    public static class Layer2 {

        @JsonProperty("data")
        private List<Long> data = new ArrayList<Long>();
        @JsonProperty("height")
        private Long height;
        @JsonProperty("id")
        private Long id;
        @JsonProperty("name")
        private String name;
        @JsonProperty("opacity")
        private Long opacity;
        @JsonProperty("type")
        private String type;
        @JsonProperty("visible")
        private Boolean visible;
        @JsonProperty("width")
        private Long width;
        @JsonProperty("x")
        private Long x;
        @JsonProperty("y")
        private Long y;
        @JsonIgnore
        private Map<String, java.lang.Object> additionalProperties = new HashMap<String, java.lang.Object>();

        /**
         * No args constructor for use in serialization
         * 
         */
        public Layer2() {
        }

        /**
         * 
         * @param visible
         * @param data
         * @param name
         * @param width
         * @param x
         * @param y
         * @param id
         * @param opacity
         * @param type
         * @param height
         */
        public Layer2(List<Long> data, Long height, Long id, String name, Long opacity, String type, Boolean visible, Long width, Long x, Long y) {
            super();
            this.data = data;
            this.height = height;
            this.id = id;
            this.name = name;
            this.opacity = opacity;
            this.type = type;
            this.visible = visible;
            this.width = width;
            this.x = x;
            this.y = y;
        }

        @JsonProperty("data")
        public List<Long> getData() {
            return data;
        }

        @JsonProperty("data")
        public void setData(List<Long> data) {
            this.data = data;
        }

        @JsonProperty("height")
        public Long getHeight() {
            return height;
        }

        @JsonProperty("height")
        public void setHeight(Long height) {
            this.height = height;
        }

        @JsonProperty("id")
        public Long getId() {
            return id;
        }

        @JsonProperty("id")
        public void setId(Long id) {
            this.id = id;
        }

        @JsonProperty("name")
        public String getName() {
            return name;
        }

        @JsonProperty("name")
        public void setName(String name) {
            this.name = name;
        }

        @JsonProperty("opacity")
        public Long getOpacity() {
            return opacity;
        }

        @JsonProperty("opacity")
        public void setOpacity(Long opacity) {
            this.opacity = opacity;
        }

        @JsonProperty("type")
        public String getType() {
            return type;
        }

        @JsonProperty("type")
        public void setType(String type) {
            this.type = type;
        }

        @JsonProperty("visible")
        public Boolean getVisible() {
            return visible;
        }

        @JsonProperty("visible")
        public void setVisible(Boolean visible) {
            this.visible = visible;
        }

        @JsonProperty("width")
        public Long getWidth() {
            return width;
        }

        @JsonProperty("width")
        public void setWidth(Long width) {
            this.width = width;
        }

        @JsonProperty("x")
        public Long getX() {
            return x;
        }

        @JsonProperty("x")
        public void setX(Long x) {
            this.x = x;
        }

        @JsonProperty("y")
        public Long getY() {
            return y;
        }

        @JsonProperty("y")
        public void setY(Long y) {
            this.y = y;
        }

        @JsonAnyGetter
        public Map<String, java.lang.Object> getAdditionalProperties() {
            return this.additionalProperties;
        }

        @JsonAnySetter
        public void setAdditionalProperty(String name, java.lang.Object value) {
            this.additionalProperties.put(name, value);
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(TiledMap.Layer2 .class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
            sb.append("data");
            sb.append('=');
            sb.append(((this.data == null)?"<null>":this.data));
            sb.append(',');
            sb.append("height");
            sb.append('=');
            sb.append(((this.height == null)?"<null>":this.height));
            sb.append(',');
            sb.append("id");
            sb.append('=');
            sb.append(((this.id == null)?"<null>":this.id));
            sb.append(',');
            sb.append("name");
            sb.append('=');
            sb.append(((this.name == null)?"<null>":this.name));
            sb.append(',');
            sb.append("opacity");
            sb.append('=');
            sb.append(((this.opacity == null)?"<null>":this.opacity));
            sb.append(',');
            sb.append("type");
            sb.append('=');
            sb.append(((this.type == null)?"<null>":this.type));
            sb.append(',');
            sb.append("visible");
            sb.append('=');
            sb.append(((this.visible == null)?"<null>":this.visible));
            sb.append(',');
            sb.append("width");
            sb.append('=');
            sb.append(((this.width == null)?"<null>":this.width));
            sb.append(',');
            sb.append("x");
            sb.append('=');
            sb.append(((this.x == null)?"<null>":this.x));
            sb.append(',');
            sb.append("y");
            sb.append('=');
            sb.append(((this.y == null)?"<null>":this.y));
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
            result = ((result* 31)+((this.visible == null)? 0 :this.visible.hashCode()));
            result = ((result* 31)+((this.data == null)? 0 :this.data.hashCode()));
            result = ((result* 31)+((this.name == null)? 0 :this.name.hashCode()));
            result = ((result* 31)+((this.width == null)? 0 :this.width.hashCode()));
            result = ((result* 31)+((this.x == null)? 0 :this.x.hashCode()));
            result = ((result* 31)+((this.y == null)? 0 :this.y.hashCode()));
            result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
            result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
            result = ((result* 31)+((this.opacity == null)? 0 :this.opacity.hashCode()));
            result = ((result* 31)+((this.type == null)? 0 :this.type.hashCode()));
            result = ((result* 31)+((this.height == null)? 0 :this.height.hashCode()));
            return result;
        }

        @Override
        public boolean equals(java.lang.Object other) {
            if (other == this) {
                return true;
            }
            if ((other instanceof TiledMap.Layer2) == false) {
                return false;
            }
            TiledMap.Layer2 rhs = ((TiledMap.Layer2) other);
            return ((((((((((((this.visible == rhs.visible)||((this.visible!= null)&&this.visible.equals(rhs.visible)))&&((this.data == rhs.data)||((this.data!= null)&&this.data.equals(rhs.data))))&&((this.name == rhs.name)||((this.name!= null)&&this.name.equals(rhs.name))))&&((this.width == rhs.width)||((this.width!= null)&&this.width.equals(rhs.width))))&&((this.x == rhs.x)||((this.x!= null)&&this.x.equals(rhs.x))))&&((this.y == rhs.y)||((this.y!= null)&&this.y.equals(rhs.y))))&&((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.opacity == rhs.opacity)||((this.opacity!= null)&&this.opacity.equals(rhs.opacity))))&&((this.type == rhs.type)||((this.type!= null)&&this.type.equals(rhs.type))))&&((this.height == rhs.height)||((this.height!= null)&&this.height.equals(rhs.height))));
        }

    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
        "height",
        "id",
        "name",
        "polygon",
        "properties",
        "rotation",
        "type",
        "visible",
        "width",
        "x",
        "y",
        "point",
        "gid",
        "text"
    })
    @Generated("jsonschema2pojo")
    public static class Object {

        @JsonProperty("height")
        private Double height;
        @JsonProperty("id")
        private Long id;
        @JsonProperty("name")
        private String name;
        @JsonProperty("polygon")
        private List<TiledMap.Polygon> polygon = new ArrayList<TiledMap.Polygon>();
        @JsonProperty("properties")
        private List<TiledMap.Property> properties = new ArrayList<TiledMap.Property>();
        @JsonProperty("rotation")
        private Long rotation;
        @JsonProperty("type")
        private String type;
        @JsonProperty("visible")
        private Boolean visible;
        @JsonProperty("width")
        private Double width;
        @JsonProperty("x")
        private Long x;
        @JsonProperty("y")
        private Double y;
        @JsonProperty("point")
        private Boolean point;
        @JsonProperty("gid")
        private Long gid;
        @JsonProperty("text")
        private TiledMap.Text text;
        @JsonIgnore
        private Map<String, java.lang.Object> additionalProperties = new HashMap<String, java.lang.Object>();

        /**
         * No args constructor for use in serialization
         * 
         */
        public Object() {
        }

        /**
         * 
         * @param visible
         * @param gid
         * @param rotation
         * @param type
         * @param point
         * @param polygon
         * @param name
         * @param width
         * @param x
         * @param y
         * @param id
         * @param text
         * @param properties
         * @param height
         */
        public Object(Double height, Long id, String name, List<TiledMap.Polygon> polygon, List<TiledMap.Property> properties, Long rotation, String type, Boolean visible, Double width, Long x, Double y, Boolean point, Long gid, TiledMap.Text text) {
            super();
            this.height = height;
            this.id = id;
            this.name = name;
            this.polygon = polygon;
            this.properties = properties;
            this.rotation = rotation;
            this.type = type;
            this.visible = visible;
            this.width = width;
            this.x = x;
            this.y = y;
            this.point = point;
            this.gid = gid;
            this.text = text;
        }

        @JsonProperty("height")
        public Double getHeight() {
            return height;
        }

        @JsonProperty("height")
        public void setHeight(Double height) {
            this.height = height;
        }

        @JsonProperty("id")
        public Long getId() {
            return id;
        }

        @JsonProperty("id")
        public void setId(Long id) {
            this.id = id;
        }

        @JsonProperty("name")
        public String getName() {
            return name;
        }

        @JsonProperty("name")
        public void setName(String name) {
            this.name = name;
        }

        @JsonProperty("polygon")
        public List<TiledMap.Polygon> getPolygon() {
            return polygon;
        }

        @JsonProperty("polygon")
        public void setPolygon(List<TiledMap.Polygon> polygon) {
            this.polygon = polygon;
        }

        @JsonProperty("properties")
        public List<TiledMap.Property> getProperties() {
            return properties;
        }

        @JsonProperty("properties")
        public void setProperties(List<TiledMap.Property> properties) {
            this.properties = properties;
        }

        @JsonProperty("rotation")
        public Long getRotation() {
            return rotation;
        }

        @JsonProperty("rotation")
        public void setRotation(Long rotation) {
            this.rotation = rotation;
        }

        @JsonProperty("type")
        public String getType() {
            return type;
        }

        @JsonProperty("type")
        public void setType(String type) {
            this.type = type;
        }

        @JsonProperty("visible")
        public Boolean getVisible() {
            return visible;
        }

        @JsonProperty("visible")
        public void setVisible(Boolean visible) {
            this.visible = visible;
        }

        @JsonProperty("width")
        public Double getWidth() {
            return width;
        }

        @JsonProperty("width")
        public void setWidth(Double width) {
            this.width = width;
        }

        @JsonProperty("x")
        public Long getX() {
            return x;
        }

        @JsonProperty("x")
        public void setX(Long x) {
            this.x = x;
        }

        @JsonProperty("y")
        public Double getY() {
            return y;
        }

        @JsonProperty("y")
        public void setY(Double y) {
            this.y = y;
        }

        @JsonProperty("point")
        public Boolean getPoint() {
            return point;
        }

        @JsonProperty("point")
        public void setPoint(Boolean point) {
            this.point = point;
        }

        @JsonProperty("gid")
        public Long getGid() {
            return gid;
        }

        @JsonProperty("gid")
        public void setGid(Long gid) {
            this.gid = gid;
        }

        @JsonProperty("text")
        public TiledMap.Text getText() {
            return text;
        }

        @JsonProperty("text")
        public void setText(TiledMap.Text text) {
            this.text = text;
        }

        @JsonAnyGetter
        public Map<String, java.lang.Object> getAdditionalProperties() {
            return this.additionalProperties;
        }

        @JsonAnySetter
        public void setAdditionalProperty(String name, java.lang.Object value) {
            this.additionalProperties.put(name, value);
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(TiledMap.Object.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
            sb.append("height");
            sb.append('=');
            sb.append(((this.height == null)?"<null>":this.height));
            sb.append(',');
            sb.append("id");
            sb.append('=');
            sb.append(((this.id == null)?"<null>":this.id));
            sb.append(',');
            sb.append("name");
            sb.append('=');
            sb.append(((this.name == null)?"<null>":this.name));
            sb.append(',');
            sb.append("polygon");
            sb.append('=');
            sb.append(((this.polygon == null)?"<null>":this.polygon));
            sb.append(',');
            sb.append("properties");
            sb.append('=');
            sb.append(((this.properties == null)?"<null>":this.properties));
            sb.append(',');
            sb.append("rotation");
            sb.append('=');
            sb.append(((this.rotation == null)?"<null>":this.rotation));
            sb.append(',');
            sb.append("type");
            sb.append('=');
            sb.append(((this.type == null)?"<null>":this.type));
            sb.append(',');
            sb.append("visible");
            sb.append('=');
            sb.append(((this.visible == null)?"<null>":this.visible));
            sb.append(',');
            sb.append("width");
            sb.append('=');
            sb.append(((this.width == null)?"<null>":this.width));
            sb.append(',');
            sb.append("x");
            sb.append('=');
            sb.append(((this.x == null)?"<null>":this.x));
            sb.append(',');
            sb.append("y");
            sb.append('=');
            sb.append(((this.y == null)?"<null>":this.y));
            sb.append(',');
            sb.append("point");
            sb.append('=');
            sb.append(((this.point == null)?"<null>":this.point));
            sb.append(',');
            sb.append("gid");
            sb.append('=');
            sb.append(((this.gid == null)?"<null>":this.gid));
            sb.append(',');
            sb.append("text");
            sb.append('=');
            sb.append(((this.text == null)?"<null>":this.text));
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
            result = ((result* 31)+((this.visible == null)? 0 :this.visible.hashCode()));
            result = ((result* 31)+((this.gid == null)? 0 :this.gid.hashCode()));
            result = ((result* 31)+((this.rotation == null)? 0 :this.rotation.hashCode()));
            result = ((result* 31)+((this.type == null)? 0 :this.type.hashCode()));
            result = ((result* 31)+((this.point == null)? 0 :this.point.hashCode()));
            result = ((result* 31)+((this.polygon == null)? 0 :this.polygon.hashCode()));
            result = ((result* 31)+((this.name == null)? 0 :this.name.hashCode()));
            result = ((result* 31)+((this.width == null)? 0 :this.width.hashCode()));
            result = ((result* 31)+((this.x == null)? 0 :this.x.hashCode()));
            result = ((result* 31)+((this.y == null)? 0 :this.y.hashCode()));
            result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
            result = ((result* 31)+((this.text == null)? 0 :this.text.hashCode()));
            result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
            result = ((result* 31)+((this.properties == null)? 0 :this.properties.hashCode()));
            result = ((result* 31)+((this.height == null)? 0 :this.height.hashCode()));
            return result;
        }

        @Override
        public boolean equals(java.lang.Object other) {
            if (other == this) {
                return true;
            }
            if ((other instanceof TiledMap.Object) == false) {
                return false;
            }
            TiledMap.Object rhs = ((TiledMap.Object) other);
            return ((((((((((((((((this.visible == rhs.visible)||((this.visible!= null)&&this.visible.equals(rhs.visible)))&&((this.gid == rhs.gid)||((this.gid!= null)&&this.gid.equals(rhs.gid))))&&((this.rotation == rhs.rotation)||((this.rotation!= null)&&this.rotation.equals(rhs.rotation))))&&((this.type == rhs.type)||((this.type!= null)&&this.type.equals(rhs.type))))&&((this.point == rhs.point)||((this.point!= null)&&this.point.equals(rhs.point))))&&((this.polygon == rhs.polygon)||((this.polygon!= null)&&this.polygon.equals(rhs.polygon))))&&((this.name == rhs.name)||((this.name!= null)&&this.name.equals(rhs.name))))&&((this.width == rhs.width)||((this.width!= null)&&this.width.equals(rhs.width))))&&((this.x == rhs.x)||((this.x!= null)&&this.x.equals(rhs.x))))&&((this.y == rhs.y)||((this.y!= null)&&this.y.equals(rhs.y))))&&((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id))))&&((this.text == rhs.text)||((this.text!= null)&&this.text.equals(rhs.text))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.properties == rhs.properties)||((this.properties!= null)&&this.properties.equals(rhs.properties))))&&((this.height == rhs.height)||((this.height!= null)&&this.height.equals(rhs.height))));
        }

    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
        "x",
        "y"
    })
    @Generated("jsonschema2pojo")
    public static class Polygon {

        @JsonProperty("x")
        private Double x;
        @JsonProperty("y")
        private Long y;
        @JsonIgnore
        private Map<String, java.lang.Object> additionalProperties = new HashMap<String, java.lang.Object>();

        /**
         * No args constructor for use in serialization
         * 
         */
        public Polygon() {
        }

        /**
         * 
         * @param x
         * @param y
         */
        public Polygon(Double x, Long y) {
            super();
            this.x = x;
            this.y = y;
        }

        @JsonProperty("x")
        public Double getX() {
            return x;
        }

        @JsonProperty("x")
        public void setX(Double x) {
            this.x = x;
        }

        @JsonProperty("y")
        public Long getY() {
            return y;
        }

        @JsonProperty("y")
        public void setY(Long y) {
            this.y = y;
        }

        @JsonAnyGetter
        public Map<String, java.lang.Object> getAdditionalProperties() {
            return this.additionalProperties;
        }

        @JsonAnySetter
        public void setAdditionalProperty(String name, java.lang.Object value) {
            this.additionalProperties.put(name, value);
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(TiledMap.Polygon.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
            sb.append("x");
            sb.append('=');
            sb.append(((this.x == null)?"<null>":this.x));
            sb.append(',');
            sb.append("y");
            sb.append('=');
            sb.append(((this.y == null)?"<null>":this.y));
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
            result = ((result* 31)+((this.x == null)? 0 :this.x.hashCode()));
            result = ((result* 31)+((this.y == null)? 0 :this.y.hashCode()));
            result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
            return result;
        }

        @Override
        public boolean equals(java.lang.Object other) {
            if (other == this) {
                return true;
            }
            if ((other instanceof TiledMap.Polygon) == false) {
                return false;
            }
            TiledMap.Polygon rhs = ((TiledMap.Polygon) other);
            return ((((this.x == rhs.x)||((this.x!= null)&&this.x.equals(rhs.x)))&&((this.y == rhs.y)||((this.y!= null)&&this.y.equals(rhs.y))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))));
        }

    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
        "name",
        "type",
        "value"
    })
    @Generated("jsonschema2pojo")
    public static class Property {

        @JsonProperty("name")
        private String name;
        @JsonProperty("type")
        private String type;
        @JsonProperty("value")
        private String value;
        @JsonIgnore
        private Map<String, java.lang.Object> additionalProperties = new HashMap<String, java.lang.Object>();

        /**
         * No args constructor for use in serialization
         * 
         */
        public Property() {
        }

        /**
         * 
         * @param name
         * @param type
         * @param value
         */
        public Property(String name, String type, String value) {
            super();
            this.name = name;
            this.type = type;
            this.value = value;
        }

        @JsonProperty("name")
        public String getName() {
            return name;
        }

        @JsonProperty("name")
        public void setName(String name) {
            this.name = name;
        }

        @JsonProperty("type")
        public String getType() {
            return type;
        }

        @JsonProperty("type")
        public void setType(String type) {
            this.type = type;
        }

        @JsonProperty("value")
        public String getValue() {
            return value;
        }

        @JsonProperty("value")
        public void setValue(String value) {
            this.value = value;
        }

        @JsonAnyGetter
        public Map<String, java.lang.Object> getAdditionalProperties() {
            return this.additionalProperties;
        }

        @JsonAnySetter
        public void setAdditionalProperty(String name, java.lang.Object value) {
            this.additionalProperties.put(name, value);
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(TiledMap.Property.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
            sb.append("name");
            sb.append('=');
            sb.append(((this.name == null)?"<null>":this.name));
            sb.append(',');
            sb.append("type");
            sb.append('=');
            sb.append(((this.type == null)?"<null>":this.type));
            sb.append(',');
            sb.append("value");
            sb.append('=');
            sb.append(((this.value == null)?"<null>":this.value));
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
            result = ((result* 31)+((this.name == null)? 0 :this.name.hashCode()));
            result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
            result = ((result* 31)+((this.type == null)? 0 :this.type.hashCode()));
            result = ((result* 31)+((this.value == null)? 0 :this.value.hashCode()));
            return result;
        }

        @Override
        public boolean equals(java.lang.Object other) {
            if (other == this) {
                return true;
            }
            if ((other instanceof TiledMap.Property) == false) {
                return false;
            }
            TiledMap.Property rhs = ((TiledMap.Property) other);
            return (((((this.name == rhs.name)||((this.name!= null)&&this.name.equals(rhs.name)))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.type == rhs.type)||((this.type!= null)&&this.type.equals(rhs.type))))&&((this.value == rhs.value)||((this.value!= null)&&this.value.equals(rhs.value))));
        }

    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
        "fontfamily",
        "pixelsize",
        "text",
        "wrap"
    })
    @Generated("jsonschema2pojo")
    public static class Text {

        @JsonProperty("fontfamily")
        private String fontfamily;
        @JsonProperty("pixelsize")
        private Long pixelsize;
        @JsonProperty("text")
        private String text;
        @JsonProperty("wrap")
        private Boolean wrap;
        @JsonIgnore
        private Map<String, java.lang.Object> additionalProperties = new HashMap<String, java.lang.Object>();

        /**
         * No args constructor for use in serialization
         * 
         */
        public Text() {
        }

        /**
         * 
         * @param fontfamily
         * @param text
         * @param wrap
         * @param pixelsize
         */
        public Text(String fontfamily, Long pixelsize, String text, Boolean wrap) {
            super();
            this.fontfamily = fontfamily;
            this.pixelsize = pixelsize;
            this.text = text;
            this.wrap = wrap;
        }

        @JsonProperty("fontfamily")
        public String getFontfamily() {
            return fontfamily;
        }

        @JsonProperty("fontfamily")
        public void setFontfamily(String fontfamily) {
            this.fontfamily = fontfamily;
        }

        @JsonProperty("pixelsize")
        public Long getPixelsize() {
            return pixelsize;
        }

        @JsonProperty("pixelsize")
        public void setPixelsize(Long pixelsize) {
            this.pixelsize = pixelsize;
        }

        @JsonProperty("text")
        public String getText() {
            return text;
        }

        @JsonProperty("text")
        public void setText(String text) {
            this.text = text;
        }

        @JsonProperty("wrap")
        public Boolean getWrap() {
            return wrap;
        }

        @JsonProperty("wrap")
        public void setWrap(Boolean wrap) {
            this.wrap = wrap;
        }

        @JsonAnyGetter
        public Map<String, java.lang.Object> getAdditionalProperties() {
            return this.additionalProperties;
        }

        @JsonAnySetter
        public void setAdditionalProperty(String name, java.lang.Object value) {
            this.additionalProperties.put(name, value);
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(TiledMap.Text.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
            sb.append("fontfamily");
            sb.append('=');
            sb.append(((this.fontfamily == null)?"<null>":this.fontfamily));
            sb.append(',');
            sb.append("pixelsize");
            sb.append('=');
            sb.append(((this.pixelsize == null)?"<null>":this.pixelsize));
            sb.append(',');
            sb.append("text");
            sb.append('=');
            sb.append(((this.text == null)?"<null>":this.text));
            sb.append(',');
            sb.append("wrap");
            sb.append('=');
            sb.append(((this.wrap == null)?"<null>":this.wrap));
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
            result = ((result* 31)+((this.fontfamily == null)? 0 :this.fontfamily.hashCode()));
            result = ((result* 31)+((this.text == null)? 0 :this.text.hashCode()));
            result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
            result = ((result* 31)+((this.wrap == null)? 0 :this.wrap.hashCode()));
            result = ((result* 31)+((this.pixelsize == null)? 0 :this.pixelsize.hashCode()));
            return result;
        }

        @Override
        public boolean equals(java.lang.Object other) {
            if (other == this) {
                return true;
            }
            if ((other instanceof TiledMap.Text) == false) {
                return false;
            }
            TiledMap.Text rhs = ((TiledMap.Text) other);
            return ((((((this.fontfamily == rhs.fontfamily)||((this.fontfamily!= null)&&this.fontfamily.equals(rhs.fontfamily)))&&((this.text == rhs.text)||((this.text!= null)&&this.text.equals(rhs.text))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.wrap == rhs.wrap)||((this.wrap!= null)&&this.wrap.equals(rhs.wrap))))&&((this.pixelsize == rhs.pixelsize)||((this.pixelsize!= null)&&this.pixelsize.equals(rhs.pixelsize))));
        }

    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
        "firstgid",
        "source"
    })
    @Generated("jsonschema2pojo")
    public static class Tileset {

        @JsonProperty("firstgid")
        private Long firstgid;
        @JsonProperty("source")
        private String source;
        @JsonIgnore
        private Map<String, java.lang.Object> additionalProperties = new HashMap<String, java.lang.Object>();

        /**
         * No args constructor for use in serialization
         * 
         */
        public Tileset() {
        }

        /**
         * 
         * @param firstgid
         * @param source
         */
        public Tileset(Long firstgid, String source) {
            super();
            this.firstgid = firstgid;
            this.source = source;
        }

        @JsonProperty("firstgid")
        public Long getFirstgid() {
            return firstgid;
        }

        @JsonProperty("firstgid")
        public void setFirstgid(Long firstgid) {
            this.firstgid = firstgid;
        }

        @JsonProperty("source")
        public String getSource() {
            return source;
        }

        @JsonProperty("source")
        public void setSource(String source) {
            this.source = source;
        }

        @JsonAnyGetter
        public Map<String, java.lang.Object> getAdditionalProperties() {
            return this.additionalProperties;
        }

        @JsonAnySetter
        public void setAdditionalProperty(String name, java.lang.Object value) {
            this.additionalProperties.put(name, value);
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(TiledMap.Tileset.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
            sb.append("firstgid");
            sb.append('=');
            sb.append(((this.firstgid == null)?"<null>":this.firstgid));
            sb.append(',');
            sb.append("source");
            sb.append('=');
            sb.append(((this.source == null)?"<null>":this.source));
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
            result = ((result* 31)+((this.firstgid == null)? 0 :this.firstgid.hashCode()));
            result = ((result* 31)+((this.source == null)? 0 :this.source.hashCode()));
            result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
            return result;
        }

        @Override
        public boolean equals(java.lang.Object other) {
            if (other == this) {
                return true;
            }
            if ((other instanceof TiledMap.Tileset) == false) {
                return false;
            }
            TiledMap.Tileset rhs = ((TiledMap.Tileset) other);
            return ((((this.firstgid == rhs.firstgid)||((this.firstgid!= null)&&this.firstgid.equals(rhs.firstgid)))&&((this.source == rhs.source)||((this.source!= null)&&this.source.equals(rhs.source))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))));
        }

    }

}
