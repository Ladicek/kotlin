/*
 * Copyright 2010-2013 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jetbrains.jet.plugin.refactoring.safeDelete;

import com.intellij.psi.PsiElement;
import com.intellij.refactoring.safeDelete.usageInfo.SafeDeleteReferenceSimpleDeleteUsageInfo;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.jet.lang.psi.*;

public class SafeDeleteTypeArgumentListUsageInfo extends SafeDeleteReferenceSimpleDeleteUsageInfo {
    public SafeDeleteTypeArgumentListUsageInfo(@NotNull JetTypeProjection typeProjection, @NotNull JetTypeParameter parameter) {
        super(typeProjection, parameter, true);
    }

    @Override
    public void deleteElement() throws IncorrectOperationException {
        PsiElement element = getElement();
        if (element != null) {
            KotlinSafeDeleteProcessor.deleteElementAndCleanParent(element);
        }
    }
}