package org.zerock.clubapi.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPoster is a Querydsl query type for Poster
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPoster extends EntityPathBase<Poster> {

    private static final long serialVersionUID = 665783162L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPoster poster = new QPoster("poster");

    public final QBiMovie bimovie;

    public final StringPath fname = createString("fname");

    public final NumberPath<Integer> idx = createNumber("idx", Integer.class);

    public final NumberPath<Long> ino = createNumber("ino", Long.class);

    public QPoster(String variable) {
        this(Poster.class, forVariable(variable), INITS);
    }

    public QPoster(Path<? extends Poster> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPoster(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPoster(PathMetadata metadata, PathInits inits) {
        this(Poster.class, metadata, inits);
    }

    public QPoster(Class<? extends Poster> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.bimovie = inits.isInitialized("bimovie") ? new QBiMovie(forProperty("bimovie")) : null;
    }

}

