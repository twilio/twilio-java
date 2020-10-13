package com.twilio.type;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;

import java.util.Objects;

/**
 * Recording Rule
 *
 * <p>
 * For more information see:
 * <a href=https://www.twilio.com/docs/video/api/recording-start/stop#specifying-sr>Specifying Recording Rules</a>
 * </p>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class RecordingRule implements Rule {

    private static final RecordingRule recordAll = builder().withType(Type.INCLUDE).withAll().build();
    private static final RecordingRule recordNone = builder().withType(Type.EXCLUDE).withAll().build();

    @JsonProperty("type")
    private final Type type;

    @JsonProperty("all")
    private final Boolean all;

    @JsonProperty("publisher")
    private final String publisher;

    @JsonProperty("track")
    private final String track;

    @JsonProperty("kind")
    private final Kind kind;

    public RecordingRule(@JsonProperty("type") final Type type,
            @JsonProperty("all") final Boolean all,
            @JsonProperty("publisher") final String publisher,
            @JsonProperty("track") final String track,
            @JsonProperty("kind") final Kind kind) {
        this.type = type;
        this.all = all;
        this.publisher = publisher;
        this.track = track;
        this.kind = kind;
    }

    public RecordingRule() {
        this.type = null;
        this.all = null;
        this.publisher = null;
        this.track = null;
        this.kind = null;
    }

    public static BuilderStart builder() {
        return new Builder();
    }

    public interface BuilderStart {
        BuilderMiddle withType(final Type type);
    }

    public interface BuilderMiddle {
        BuilderMiddleBuild withPublisher(final String publisher);
        BuilderMiddleBuild withKind(final Kind kind);
        BuilderMiddleBuild withTrack(final String track);
        BuilderBuild withAll();
    }

    public interface BuilderMiddleBuild {
        BuilderMiddleBuild withPublisher(final String publisher);
        BuilderMiddleBuild withKind(final Kind kind);
        BuilderMiddleBuild withTrack(final String track);
        RecordingRule build();
    }

    public interface BuilderBuild {
        RecordingRule build();
    }

    public static class Builder implements
            BuilderStart,
            BuilderMiddle,
            BuilderMiddleBuild,
            BuilderBuild {
        private Type type;
        private Boolean all;
        private String publisher;
        private Kind kind;
        private String track;

        private Builder() {
        }

        public BuilderMiddle withType(final Type type) {
            this.type = type;
            return this;
        }

        public BuilderBuild withAll() {
            this.all = true;
            return this;
        }
        public BuilderMiddleBuild withPublisher(final String publisher) {
            this.publisher = publisher;
            return this;
        }
        public BuilderMiddleBuild withKind(final Kind kind) {
            this.kind = kind;
            return this;
        }
        public BuilderMiddleBuild withTrack(final String track) {
            this.track = track;
            return this;
        }

        private boolean hasOneFilter() {
            // at least one filter must be set
            return this.kind != null
                    || this.all != null
                    || this.track != null
                    || this.publisher != null;
        }

        private boolean hasType() {
            // every rule must have a type
            return this.type != null;
        }

        public RecordingRule build() {
            if (!hasType()) {
                throw new IllegalArgumentException("Recording Rule must have a type");
            }
            if (!hasOneFilter()) {
                throw new IllegalArgumentException("Recording Rule must have at least one filter");
            }

            return new RecordingRule(this.type, this.all, this.publisher, this.track, this.kind);
        }
    }

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public Boolean getAll() {
        return all;
    }

    @Override
    public String getPublisher() {
        return publisher;
    }

    @Override
    public String getTrack() {
        return track;
    }

    @Override
    public Kind getKind() {
        return kind;
    }

    public static RecordingRule all() {
        return recordAll;
    }

    public static RecordingRule none() {
        return recordNone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RecordingRule)) return false;
        RecordingRule that = (RecordingRule) o;
        return getType() == that.getType() &&
                Objects.equals(getAll(), that.getAll()) &&
                Objects.equals(getPublisher(), that.getPublisher()) &&
                Objects.equals(getTrack(), that.getTrack()) &&
                getKind() == that.getKind();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getType(), getAll(), getPublisher(), getTrack(), getKind());
    }
}
