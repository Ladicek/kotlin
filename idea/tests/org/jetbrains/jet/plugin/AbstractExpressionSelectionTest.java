/*
 * Copyright 2010-2014 JetBrains s.r.o.
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

package org.jetbrains.jet.plugin;

import com.intellij.testFramework.LightCodeInsightTestCase;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.jet.JetTestUtils;
import org.jetbrains.jet.lang.psi.JetExpression;
import org.jetbrains.jet.lang.psi.JetFile;
import org.jetbrains.jet.plugin.refactoring.JetRefactoringUtil;

public abstract class AbstractExpressionSelectionTest extends LightCodeInsightTestCase {

    public void doTestExpressionSelection(@NotNull String path) throws Exception {
        configureByFile(path);
        final String expectedExpression = JetTestUtils.getLastCommentInFile((JetFile) getFile());

        try {
            JetRefactoringUtil.selectExpression(getEditor(), getFile(), new JetRefactoringUtil.SelectExpressionCallback() {
                @Override
                public void run(@Nullable JetExpression expression) {
                    assertNotNull("Selected expression mustn't be null", expression);
                    assertEquals(expectedExpression, expression.getText());
                }
            });
        }
        catch (JetRefactoringUtil.IntroduceRefactoringException e) {
            assertEquals(expectedExpression, "");
        }
    }

    @NotNull
    @Override
    protected String getTestDataPath() {
        return "";
    }
}
