package com.fzc.fzcsearchneo4j.relationship;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;

/**
 * @author flamenco.xxx
 * @date 2021/11/30 15:18
 */
@RelationshipProperties
public class NameToStock {

    @Id
    private Long id;


    private String name;
}
