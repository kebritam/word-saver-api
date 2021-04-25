// SPDX-License-Identifier: MIT

package com.teimour.wordsapi.modelDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author kebritam
 * Project words-api
 */

@Getter
@Setter
@AllArgsConstructor
public class ShallowWord {
    private String wordValue;
    private String wordUrl;
}
