package com.cantarino;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;

import org.junit.Assert;
import org.junit.jupiter.api.Test;


import com.cantarino.models.Produto;
import com.github.database.rider.cdi.api.DBRider;
import com.github.database.rider.core.api.dataset.DataSet;


@DBRider
@QuarkusTest
@QuarkusTestResource(DatabaseLifeCycle.class)
public class ProdutoTest {

    @Test
    @DataSet("produto.yml")
    public void test1() {

        Assert.assertEquals(1, Produto.count());

    }

}