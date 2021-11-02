/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2016-2021 Yegor Bugayenko
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package EOorg.EOeolang;

import org.eolang.AtComposite;
import org.eolang.AtFree;
import org.eolang.AtOnce;
import org.eolang.Data;
import org.eolang.Dataized;
import org.eolang.PhDefault;
import org.eolang.PhWith;
import org.eolang.Phi;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

/**
 * Test case for {@link EOarray}.
 *
 * @since 0.17
 */
public final class EOarrayEOmapTest {

    @Test
    public void mapsSimpleArray() {
        final Phi[] items = new Dataized(
            new PhWith(
                new EOarray$EOmap(
                    new Data.ToPhi(
                        new Phi[] {
                            new Data.ToPhi(5L),
                            new Data.ToPhi(1L)
                        }
                    )
                ),
                0, new EOarrayEOmapTest.Kid(Phi.Φ)
            )
        ).take(Phi[].class);
        MatcherAssert.assertThat(
            items.length,
            Matchers.equalTo(2)
        );
        MatcherAssert.assertThat(
            new Dataized(items[0]).take(String.class),
            Matchers.equalTo("5")
        );
    }

    public static class Kid extends PhDefault {
        public Kid(final Phi sigma) {
            super(sigma);
            this.add("x", new AtFree());
            this.add("φ", new AtOnce(new AtComposite(
                this, self -> new Data.ToPhi(
                    Long.toString(
                        new Dataized(
                            self.attr("x").get()
                        ).take(Long.class)
                    )
                )
            )));
        }
    }
}