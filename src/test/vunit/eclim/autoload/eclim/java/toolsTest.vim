" Author:  Eric Van Dewoestine
" Version: $Revision$
"
" Description: {{{
"   Test case for tools.vim
"
" License:
"
" Copyright (c) 2005 - 2006
"
" Licensed under the Apache License, Version 2.0 (the "License");
" you may not use this file except in compliance with the License.
" You may obtain a copy of the License at
"
"      http://www.apache.org/licenses/LICENSE-2.0
"
" Unless required by applicable law or agreed to in writing, software
" distributed under the License is distributed on an "AS IS" BASIS,
" WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
" See the License for the specific language governing permissions and
" limitations under the License.
"
" }}}

" TestJps() {{{
function! TestJps ()
  Jps

  call VUAssertEquals('Java_Processes', bufname('%'), 'Jps window not opened.')

  bdelete!
endfunction " }}}

" vim:ft=vim:fdm=marker