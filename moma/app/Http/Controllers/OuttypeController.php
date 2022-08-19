<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\User;
use App\Models\Income;
use App\Models\Intype;
use App\Models\Outcome;
use App\Models\Outtype;

class OuttypeController extends Controller
{
    //
    public function getType(Request $request)
    {
        $userid = $request->user_id;
        $outtype = Outtype::where(["user_id" => $userid])->get();
        if(isset($outtype))
        {
            return response()->json([
                'success'=>true,
                'outtype'=> $outtype,
            ]);
        }
        else
        {
            return response()->json([
                'success' => false,
                'outtype'=> null,
            ]);
        }
    }

    public function addType(Request $request)
    {
        $outtype = new Outtype();

        $outtype->outtype_name = $request->outtype_name;
        $outtype->user_id = $request->user_id;

        $outtype->save();

        $list = Outtype::where(["user_id" =>$request->user_id])->get();
        return response()->json([
            'success'=>true,
            'outtype'=> $list,
        ]);
    }
}
