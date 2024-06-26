package org.zerock.clubapi.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBiMovie is a Querydsl query type for BiMovie
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QBiMovie extends EntityPathBase<BiMovie> {

    private static final long serialVersionUID = -582709604L;

    public static final QBiMovie biMovie = new QBiMovie("biMovie");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final NumberPath<Long> mno = createNumber("mno", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modDate = _super.modDate;

    public final ListPath<Poster, QPoster> posterList = this.<Poster, QPoster>createList("posterList", Poster.class, QPoster.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public final StringPath title = createString("title");

    public QBiMovie(String variable) {
        super(BiMovie.class, forVariable(variable));
    }

    public QBiMovie(Path<? extends BiMovie> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBiMovie(PathMetadata metadata) {
        super(BiMovie.class, metadata);
    }

}

