package com.zhoupu.zplogsbatch.mongodb;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QSaasLogs is a Querydsl query type for SaasLogs
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSaasLogs extends EntityPathBase<SaasLogs> {

    private static final long serialVersionUID = -1879651188L;

    public static final QSaasLogs saasLogs = new QSaasLogs("saasLogs");

    public final StringPath action = createString("action");

    public final StringPath area = createString("area");

    public final StringPath bname = createString("bname");

    public final NumberPath<Long> cid = createNumber("cid", Long.class);

    public final StringPath cname = createString("cname");

    public final StringPath date = createString("date");

    public final StringPath id = createString("id");

    public final StringPath ip = createString("ip");

    public final StringPath os = createString("os");

    public final StringPath params = createString("params");

    public final StringPath result = createString("result");

    public final BooleanPath success = createBoolean("success");

    public final StringPath timer = createString("timer");

    public final NumberPath<Long> uid = createNumber("uid", Long.class);

    public final StringPath uname = createString("uname");

    public final StringPath url = createString("url");

    public QSaasLogs(String variable) {
        super(SaasLogs.class, forVariable(variable));
    }

    public QSaasLogs(Path<? extends SaasLogs> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSaasLogs(PathMetadata metadata) {
        super(SaasLogs.class, metadata);
    }

}

