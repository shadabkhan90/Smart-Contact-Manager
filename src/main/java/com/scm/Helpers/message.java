package com.scm.Helpers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Setter
@Getter
@NoArgsConstructor  
@AllArgsConstructor
public class message {

    private String content;
    @Builder.Default
    private messagetype type = messagetype.GREEN;

}
