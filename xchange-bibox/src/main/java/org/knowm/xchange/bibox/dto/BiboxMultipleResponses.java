package org.knowm.xchange.bibox.dto;

import java.util.List;

/**
 * Result of batched calls (all POST requests)
 *
 * @author odrotleff
 */
public class BiboxMultipleResponses<T> extends BiboxResponse<List<BiboxResponse<T>>> {
}