package com.cantarino.resources;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.cantarino.dto.ProdutoDTO;
import com.cantarino.models.Produto;


@Path("/api/produtos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProdutoResource {


    @GET
    public List<Produto> buscarTodosProdutos() {
        return Produto.listAll();
    }

    @POST
    @Transactional
    public Produto add(ProdutoDTO produto)
    {
        Produto p = new Produto();
        p.nome = produto.nome;
        p.preco = produto.preco;
        p.persist();


        return p;
    }


    @PUT
    @Path("{id}")
    @Transactional
    public Produto update(@PathParam("id")Long id, ProdutoDTO produto)
    {
        Optional<Produto> retornoProduto = Produto.findByIdOptional(id);
        if(!retornoProduto.isPresent())
           throw new NotFoundException("Produto nao encontrado");



        Produto p = retornoProduto.get();
        p.nome = produto.nome;
        p.preco = produto.preco;
        p.persist();


        return p;
    }


    @DELETE
    @Path("{id}")
    @Transactional
    public void delete(@PathParam("id")Long id)
    {
        Optional<Produto> retornoProduto = Produto.findByIdOptional(id);
        retornoProduto.ifPresentOrElse(Produto::delete,
                     () -> {
                        throw new NotFoundException("Produto nao encontrado");
                      });
    }
}