package br.unitins.topicos1.coffeebreak.converter.jpa;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import br.unitins.topicos1.coffeebreak.model.TipoProduto;

@Converter(autoApply = true)
public class TipoProdutoConverter implements AttributeConverter<TipoProduto, Integer> {

	@Override
	public Integer convertToDatabaseColumn(TipoProduto tipoProduto) {
		return tipoProduto.getId();
	}

	@Override
	public TipoProduto convertToEntityAttribute(Integer id) {
		return TipoProduto.valueOf(id);
	}

}
