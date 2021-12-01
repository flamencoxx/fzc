package com.fzc.fzcsearchneo4j.empty;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

/**
 * @author flamenco.xxx
 * @date 2021/11/30 16:44
 */
@Node("StockSector")
@Data
@Builder
public class StockSector {

    @Id
    @GeneratedValue
    private Long id;

    @Property("sector")
    private String sector;

}
