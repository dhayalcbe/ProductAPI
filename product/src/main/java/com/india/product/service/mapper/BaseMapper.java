/**
 *
 */
package com.india.product.service.mapper;

import java.util.List;

/**
 * @author Annadurai
 *
 */
public interface BaseMapper<D, E> {

	E toEntity(D dto);

	D toDto(E entity);

	List<E> toEntity(List<D> dtoList);

	List<D> toDto(List<E> entityList);
}
